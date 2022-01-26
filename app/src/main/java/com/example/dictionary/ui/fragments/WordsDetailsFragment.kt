package com.example.dictionary.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionary.App
import com.example.dictionary.R
import com.example.dictionary.databinding.FragmentWordsDetailsBinding
import com.example.dictionary.model.model.data.*
import com.example.dictionary.model.navigation.IDictionaryAppScreens
import com.example.dictionary.ui.adapters.ResultListRVAdapter
import com.example.dictionary.ui.base.BaseFragment
import com.example.dictionary.ui.navigation.AndroidAppScreens
import com.example.dictionary.ui.viewmodel.WordsDetailsFragmentViewModel
import com.github.terrakok.cicerone.Router
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class WordsDetailsFragment : BaseFragment<ScreenData, WordsDetailsFragmentViewModel>() {
    private var vb: FragmentWordsDetailsBinding? = null
    override lateinit var viewModel: WordsDetailsFragmentViewModel

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var screens : IDictionaryAppScreens

    @Inject
    lateinit var router: Router


    private var adapter: ResultListRVAdapter? = null

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"

        fun newInstance(): Fragment = WordsDetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentWordsDetailsBinding.inflate(inflater, container, false).also {
        initAppBar()
        vb = it
    }.root

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAppBar()
        initSearchFAB()
        initRV()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = viewModelFactory.create(WordsDetailsFragmentViewModel::class.java)
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
    }

    override fun renderData(data: ScreenData) = when(data) {
        is SSuccess<*> -> {
            val list = data.data as List<DataModel>
            showData(list)
        }
        is SInProgress -> showLoading(data.progress)
        is SError -> showError(data.exception)
        else -> Unit
    }

    fun showMessage(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    private fun initAppBar() {
        vb?.toolbar?.inflateMenu(R.menu.menu_words_details_fragment)
        vb?.toolbar?.title = requireActivity().getString(R.string.app_name)
    }

    private fun initSearchFAB() {
        vb?.searchFab?.setOnClickListener {
            showWordSearchDialog()
        }
    }

    private fun initRV() {
        vb?.resultListRv?.layoutManager = LinearLayoutManager(requireContext())
        adapter = ResultListRVAdapter(itemClickListener = {
            adapter?.words?.get(it.pos)?.text ?: "Empty"
        })
        vb?.resultListRv?.adapter = adapter
    }

    private fun showData(data: List<DataModel>) {

    }

    private fun showWordSearchDialog() {
        val searchDialogFragment = BottomSearchDialogFragment.newInstance()
        searchDialogFragment.setOnSearchClickListener(object :
            BottomSearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                viewModel.translate(searchWord, true)
            }
        })
        searchDialogFragment.show(
            requireActivity().supportFragmentManager,
            BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
        )
    }

    private fun wordsListChanged(size: Int) {
        if (size == 0) {
            showErrorScreen(getString(R.string.empty_server_response_on_success))
        } else {
            adapter?.apply { notifyDataSetChanged() }
            showViewSuccess()
        }
    }

    private fun showLoading(progress: Int?) {
        showViewLoading()
        progress?.let {
            vb?.apply {
                progressBarHorizontal.visibility = View.VISIBLE
                progressBarRound.visibility = View.GONE
                progressBarHorizontal.progress = progress
            }
        } ?: vb?.apply {
            progressBarHorizontal.visibility = View.GONE
            progressBarRound.visibility = View.VISIBLE
        }
    }

    private fun showError(error: Throwable) {
        showErrorScreen(error.message)
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        vb?.apply {
            errorTextview.text = error ?: getString(R.string.undefined_error)
            reloadButton.setOnClickListener {
                viewModel.translate("hi", true)
            }
        }
    }

    private fun showViewSuccess() = vb?.apply {
        successLayout.visibility = View.VISIBLE
        loadingFrameLayout.visibility = View.GONE
        errorLinearLayout.visibility = View.GONE
    }


    private fun showViewLoading() = vb?.apply {
        successLayout.visibility = View.GONE
        loadingFrameLayout.visibility = View.VISIBLE
        errorLinearLayout.visibility = View.GONE
    }

    private fun showViewError() = vb?.apply {
        successLayout.visibility = View.GONE
        loadingFrameLayout.visibility = View.GONE
        errorLinearLayout.visibility = View.VISIBLE
    }



}
package com.example.dictionary.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionary.App
import com.example.dictionary.R
import com.example.dictionary.databinding.FragmentWordsDetailsBinding
import com.example.dictionary.mvp.presenter.Presenter
import com.example.dictionary.mvp.presenter.WordsDetailsFragmentPresenter
import com.example.dictionary.mvp.views.IWordsDetailsView
import com.example.dictionary.ui.adapters.ResultListRVAdapter
import com.example.dictionary.ui.base.BaseFragment
import com.example.dictionary.ui.navigation.AndroidAppScreens
import geekbrains.ru.translator.rx.SchedulerProvider


class WordsDetailsFragment : BaseFragment<IWordsDetailsView>(), IWordsDetailsView {
    private var vb: FragmentWordsDetailsBinding? = null

    private val router = App.instance.router
    private val screens = AndroidAppScreens()

    private lateinit var presenter: WordsDetailsFragmentPresenter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAppBar()
        initSearchFAB()
        initRV()
    }

    override fun createPresenter(): Presenter<IWordsDetailsView> =
        WordsDetailsFragmentPresenter(router, screens, SchedulerProvider()).also {
            presenter = it
        }

    override fun showMessage(text: String) {
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
        adapter = ResultListRVAdapter(presenter.wordsListPresenter)
        vb?.resultListRv?.adapter = adapter
    }

    private fun showWordSearchDialog() {
        val searchDialogFragment = BottomSearchDialogFragment.newInstance()
        searchDialogFragment.setOnSearchClickListener(object :
            BottomSearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                presenter.translate(searchWord, true)
            }
        })
        searchDialogFragment.show(
            requireActivity().supportFragmentManager,
            BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
        )
    }

    override fun wordsListChanged(size: Int) {
        if (size == 0) {
            showErrorScreen(getString(R.string.empty_server_response_on_success))
        } else {
            adapter?.apply { notifyDataSetChanged() }
            showViewSuccess()
        }
    }

    override fun showLoading(progress: Int?) {
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

    override fun showError(error: Throwable) {
        showErrorScreen(error.message)
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        vb?.apply {
            errorTextview.text = error ?: getString(R.string.undefined_error)
            reloadButton.setOnClickListener {
                presenter.translate("hi", true)
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
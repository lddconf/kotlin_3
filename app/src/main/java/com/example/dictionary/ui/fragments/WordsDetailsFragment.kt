package com.example.dictionary.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dictionary.App
import com.example.dictionary.R
import com.example.dictionary.databinding.FragmentWordsDetailsBinding
import com.example.dictionary.ui.navigation.AndroidAppScreens

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class WordsDetailsFragment : Fragment() {
    private var vb: FragmentWordsDetailsBinding? = null
    private val screens = AndroidAppScreens()

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"

        fun newInstance() : Fragment = WordsDetailsFragment()
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
           //App.instance.router.navigateTo(screens.searchWindow())
            showWordSearchDialog()
        }
    }

    private fun showWordSearchDialog() {
        val searchDialogFragment = BottomSearchDialogFragment.newInstance()
        searchDialogFragment.setOnSearchClickListener(object : BottomSearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
//                presenter.getData(searchWord, true)
            }
        })
        searchDialogFragment.show( requireActivity().supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        vb?.apply {
            errorTextview.text = error ?: getString(R.string.undefined_error)
            reloadButton.setOnClickListener {
//                presenter.getData("hi", true)
            }
        }
    }

    private fun showViewSuccess() = vb?.apply {
        successLayout.visibility = View.VISIBLE
//        loading_frame_layout.visibility = View.GONE
        errorLinearLayout.visibility = View.GONE
    }


    private fun showViewLoading() = vb?.apply {
        successLayout.visibility = View.GONE
//        loading_frame_layout.visibility = View.VISIBLE
        errorLinearLayout.visibility = View.GONE
    }

    private fun showViewError() = vb?.apply {
        successLayout.visibility = View.GONE
//        loading_frame_layout.visibility = View.GONE
        errorLinearLayout.visibility = View.VISIBLE
    }
}
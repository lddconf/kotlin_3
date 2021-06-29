package com.example.dictionary.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dictionary.R
import com.example.dictionary.databinding.FragmentWordsDetailsBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class WordsDetailsFragment : Fragment() {
    private var vb: FragmentWordsDetailsBinding? = null

    companion object {
        fun getInstance() : Fragment = WordsDetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentWordsDetailsBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    fun appBarInit() {
//        requireActivity().setActionBar( vb?.root?.toolbar )
    }
}
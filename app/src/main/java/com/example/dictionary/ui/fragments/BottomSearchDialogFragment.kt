package com.example.dictionary.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dictionary.databinding.FragmentSearchDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.dictionary.utils.getEmptyString

class BottomSearchDialogFragment : BottomSheetDialogFragment() {
    private var vb: FragmentSearchDialogBinding? = null
    private var onSearchClickListener: OnSearchClickListener? = null

    private val textWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            vb?.apply {
                if (searchEditText.text != null && !searchEditText.text.toString().isEmpty()) {
                    searchButtonTextView.isEnabled = true
                    clearTextImageview.visibility = View.VISIBLE
                } else {
                    searchButtonTextView.isEnabled = false
                    clearTextImageview.visibility = View.GONE
                }
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(s: Editable) {}
    }

    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(vb?.searchEditText?.text.toString())
            dismiss()
        }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    interface OnSearchClickListener {

        fun onClick(searchWord: String)
    }

    companion object {
        fun newInstance() = BottomSearchDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSearchDialogBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vb?.apply {
            searchButtonTextView.setOnClickListener(onSearchButtonClickListener)
            searchEditText.addTextChangedListener(textWatcher)
        }
        addOnClearClickListener()
    }

    private fun addOnClearClickListener() {
        vb?.apply {
            clearTextImageview.setOnClickListener {
                searchEditText.setText(String.getEmptyString())
                searchButtonTextView.isEnabled = false
            }
        }
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
        vb = null
    }
}
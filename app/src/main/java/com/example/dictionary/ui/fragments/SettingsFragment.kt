package com.example.dictionary.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.example.dictionary.R
import com.example.dictionary.databinding.FragmentSearchDialogBinding
import com.example.dictionary.databinding.SettingsFragmentBinding
import com.example.dictionary.model.model.data.ScreenData
import com.example.dictionary.ui.base.BaseFragment
import com.example.dictionary.ui.viewmodel.SettingsViewModel
import com.example.dictionary.ui.viewmodel.WordsDetailsFragmentViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SettingsFragment : BaseFragment<ScreenData, SettingsViewModel>() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override lateinit var viewModel: SettingsViewModel

    private var vb: SettingsFragmentBinding? = null

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = SettingsFragmentBinding.inflate(inflater, container, false).also {
        AndroidSupportInjection.inject(this)
        vb = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[SettingsViewModel::class.java]
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
    }

    override fun renderData(data: ScreenData) {
        TODO("Not yet implemented")
    }
}
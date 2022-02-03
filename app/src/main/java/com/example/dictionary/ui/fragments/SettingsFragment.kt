package com.example.dictionary.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dictionary.databinding.SettingsFragmentBinding
import com.example.dictionary.model.model.data.*
import com.example.dictionary.model.model.data.settings.SettingsHolder
import com.example.dictionary.ui.base.BaseFragment
import com.example.dictionary.ui.viewmodel.SettingsViewModel
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
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[SettingsViewModel::class.java]
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
    }

    override fun renderData(data: ScreenData) = when (data) {
        is SSuccess<*> -> {
            val settings = data.data as SettingsHolder
            showData(settings)
        }
        is SError -> showError(data.exception)
        else -> Unit
    }

    private fun showData(settingsHolder: SettingsHolder) {

    }

    private fun showError(error: Throwable) {

    }

}
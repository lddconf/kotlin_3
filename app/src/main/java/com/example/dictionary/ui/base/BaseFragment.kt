package com.example.dictionary.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.dictionary.model.model.data.AppScreens
import com.example.dictionary.model.model.data.ScreenData
import com.example.dictionary.ui.viewmodel.BaseViewModel

abstract class BaseFragment<S : ScreenData, VM : BaseViewModel<S>> : Fragment() {
    protected open lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    abstract fun renderData( data : S )
}

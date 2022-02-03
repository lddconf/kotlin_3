package com.example.dictionary.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionary.model.model.data.AppScreens
import com.example.dictionary.ui.viewmodel.BaseViewModel

abstract class BaseActivity<S : AppScreens, VM : BaseViewModel<S>> : AppCompatActivity() {

    protected open lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    abstract fun renderData(data: S)
}

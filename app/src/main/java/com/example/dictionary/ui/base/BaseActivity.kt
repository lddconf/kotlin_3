package com.example.dictionary.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.dictionary.mvp.model.data.ScreenData
import com.example.dictionary.mvp.presenter.Presenter
import com.example.dictionary.ui.viewmodel.BaseViewModel

abstract class BaseActivity<S : ScreenData, VM : BaseViewModel<S>> : AppCompatActivity() {

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    abstract fun renderData( data : S )
}

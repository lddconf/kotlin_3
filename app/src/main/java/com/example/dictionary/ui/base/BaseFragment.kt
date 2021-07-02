package com.example.dictionary.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dictionary.mvp.model.data.ScreenData
import com.example.dictionary.mvp.presenter.Presenter
import com.example.dictionary.ui.viewmodel.BaseViewModel

abstract class BaseFragment<S : ScreenData> : Fragment() {
    protected lateinit var viewModel: BaseViewModel<S>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

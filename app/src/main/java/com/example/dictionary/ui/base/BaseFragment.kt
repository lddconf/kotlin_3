package com.example.dictionary.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dictionary.mvp.presenter.Presenter

abstract class BaseFragment<V : IView> : Fragment(), IView {

    private lateinit var internalPresenter: Presenter<V>

    protected abstract fun createPresenter(): Presenter<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        internalPresenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        internalPresenter.attachView(this as V)
    }

    override fun onStop() {
        super.onStop()
        internalPresenter.detachView(this as V)
    }
}

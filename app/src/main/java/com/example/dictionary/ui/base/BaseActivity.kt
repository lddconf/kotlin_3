package com.example.dictionary.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionary.mvp.presenter.Presenter

abstract class BaseActivity<V : IView> : AppCompatActivity(), IView {

    protected lateinit var presenter: Presenter<V>

    protected abstract fun createPresenter(): Presenter<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this as V)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this as V)
    }
}

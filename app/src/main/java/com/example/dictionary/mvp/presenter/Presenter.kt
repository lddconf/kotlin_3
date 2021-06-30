package com.example.dictionary.mvp.presenter

import com.example.dictionary.ui.base.IView


open class Presenter<V : IView> {
    var currentView: V? = null
    private set

    open fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    open fun detachView(view: V) {
        if (view == currentView) {
            currentView = null
        }
    }
}

package com.example.dictionary.model.views

import com.example.dictionary.ui.base.IView

interface IWordsDetailsView : IView {
    fun wordsListChanged(size : Int)
    fun showLoading(progress: Int?)
    fun showError(error: Throwable)

    fun showMessage(text : String)
}
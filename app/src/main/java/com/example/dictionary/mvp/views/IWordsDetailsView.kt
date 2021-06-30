package com.example.dictionary.mvp.views

import com.example.dictionary.mvp.model.data.DataModel
import com.example.dictionary.ui.base.IView

interface IWordsDetailsView : IView {
    fun wordsListChanged(size : Int)
    fun showLoading(progress: Int?)
    fun showError(error: Throwable)

    fun showMessage(text : String)
}
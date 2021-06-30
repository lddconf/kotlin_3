package com.example.dictionary.mvp.views

import com.example.dictionary.mvp.model.data.DataModel
import com.example.dictionary.ui.base.IView

interface IWordsDetailsView : IView {
    fun showWords(words : List<DataModel>)
    fun showLoading(progress: Int?)
    fun showError(error: Throwable)
}
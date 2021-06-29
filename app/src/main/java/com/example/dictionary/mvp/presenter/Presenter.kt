package com.example.dictionary.mvp.presenter

import com.example.dictionary.mvp.model.data.AppState
import geekbrains.ru.translator.view.base.View


interface Presenter<T, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}

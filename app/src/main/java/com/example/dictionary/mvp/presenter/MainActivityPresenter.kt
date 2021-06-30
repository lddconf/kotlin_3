package com.example.dictionary.mvp.presenter

import com.example.dictionary.App
import com.example.dictionary.mvp.navigation.IDictionaryAppScreens
import com.example.dictionary.mvp.rx.ISchedulerProvider
import com.example.dictionary.mvp.views.IMainView
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

class MainActivityPresenter(
    private val router: Router,
    private val screens : IDictionaryAppScreens,
) : Presenter<IMainView>() {
    override fun attachView(view: IMainView) {
        super.attachView(view)
        router.replaceScreen(screens.searchResultsWindow())
    }
}
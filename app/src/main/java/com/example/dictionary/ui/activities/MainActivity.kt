package com.example.dictionary.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.dictionary.App
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.mvp.model.data.ScreenData
import com.example.dictionary.mvp.model.data.Success
import com.example.dictionary.mvp.presenter.MainActivityPresenter
import com.example.dictionary.mvp.presenter.Presenter
import com.example.dictionary.mvp.views.IMainView
import com.example.dictionary.ui.base.BaseActivity
import com.example.dictionary.ui.navigation.AndroidAppScreens
import com.github.terrakok.cicerone.androidx.AppNavigator
import geekbrains.ru.translator.rx.SchedulerProvider

class MainActivity : BaseActivity<ScreenData>() {
    private lateinit var binding: ActivityMainBinding

    private val navigationHolder = App.instance.navigatorHolder
    private val navigator = AppNavigator(this, R.id.container)
    private val screens = AndroidAppScreens()
    private val router = App.instance.router

    companion object {
        fun getInstanceIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }

    override fun renderData(data: ScreenData) {
        when(data) {
            is Success<*> -> router.navigateTo(screens.searchResultsWindow())
            else -> return
        }
    }
}
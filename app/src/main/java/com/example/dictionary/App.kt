package com.example.dictionary

import android.app.Application
import com.example.dictionary.di.AppModule
import geekbrains.ru.translator.di.AppComponent
import geekbrains.ru.translator.di.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var appComponent: AppComponent

//    private val cicerone: Cicerone<Router> by lazy {
//        Cicerone.create()
//    }
//
//    val navigatorHolder
//        get() = cicerone.getNavigatorHolder()
//
//    val router
//        get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
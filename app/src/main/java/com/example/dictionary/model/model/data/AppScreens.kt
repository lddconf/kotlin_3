package com.example.dictionary.model.model.data

sealed class AppScreens : ScreenData()
object DictionaryWindow : AppScreens()
object SettingsWindow : AppScreens()
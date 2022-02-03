package com.example.dictionary.ui.viewmodel

import androidx.lifecycle.LiveData
import com.example.dictionary.model.interactors.MainViewModelInteractor
import com.example.dictionary.model.model.data.*
import javax.inject.Inject



class MainViewModel @Inject constructor(
    private val interactor: MainViewModelInteractor
) : BaseViewModel<AppScreens>() {
    val liveData: LiveData<AppScreens> = liveDataForViewToObserve

    fun loadSettings() {
        liveDataForViewToObserve.value = SettingsWindow
    }

    init {
        liveDataForViewToObserve.value = DictionaryWindow //Init main window
    }
}
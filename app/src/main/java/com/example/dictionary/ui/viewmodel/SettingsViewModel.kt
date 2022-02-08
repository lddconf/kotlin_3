package com.example.dictionary.ui.viewmodel

import androidx.lifecycle.LiveData
import com.example.dictionary.model.interactors.SettingsInteractor
import com.example.dictionary.model.model.data.AppScreens
import com.example.dictionary.model.model.data.ScreenData
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val interactor: SettingsInteractor
) : BaseViewModel<ScreenData>() {
    val liveData: LiveData<ScreenData> = liveDataForViewToObserve

    fun getSettings() = interactor.getData("null", false)
}
package com.example.dictionary.ui.viewmodel

import androidx.lifecycle.LiveData
import com.example.dictionary.model.model.data.SComplete
import com.example.dictionary.model.model.data.ScreenData

class MainViewModel : BaseViewModel<ScreenData>() {

    val liveData: LiveData<ScreenData> = liveDataForViewToObserve

    init {
        liveDataForViewToObserve.value = SComplete
    }
}
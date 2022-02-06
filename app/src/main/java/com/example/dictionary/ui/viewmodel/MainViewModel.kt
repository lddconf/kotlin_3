package com.example.dictionary.ui.viewmodel

import androidx.lifecycle.LiveData
import com.example.dictionary.model.interactors.Interactor
import com.example.dictionary.model.interactors.MainViewModelInteractor
import com.example.dictionary.model.interactors.WordsDetailsInteractor
import com.example.dictionary.model.model.data.SComplete
import com.example.dictionary.model.model.data.ScreenData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject



class MainViewModel @Inject constructor(
    private val interactor: MainViewModelInteractor
) : BaseViewModel<ScreenData>() {
    val liveData: LiveData<ScreenData> = liveDataForViewToObserve

    init {
        liveDataForViewToObserve.value = SComplete
    }
}
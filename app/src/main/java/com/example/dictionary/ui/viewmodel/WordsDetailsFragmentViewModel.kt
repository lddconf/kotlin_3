package com.example.dictionary.ui.viewmodel

import androidx.lifecycle.LiveData
import com.example.dictionary.model.interactors.WordsDetailsInteractor
import com.example.dictionary.model.model.data.*
import com.example.dictionary.model.model.datasource.DataSourceLocal
import com.example.dictionary.model.model.datasource.DataSourceRemote
import geekbrains.ru.translator.model.repository.RepositoryImplementation
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class WordsDetailsFragmentViewModel @Inject constructor(
    private val interactor: WordsDetailsInteractor
) : BaseViewModel<ScreenData>() {

    val liveData: LiveData<ScreenData> = liveDataForViewToObserve

    fun translate(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { loading(null) }
                .subscribeWith(getObserver())
        )
    }

    private fun loading(progress: Int?) {
        liveDataForViewToObserve.value = SInProgress(null)
    }

    private fun getObserver(): DisposableObserver<List<DataModel>> {
        return object : DisposableObserver<List<DataModel>>() {

            override fun onNext(words: List<DataModel>) {
                liveDataForViewToObserve.value = SSuccess(words)
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = SError(e)
            }

            override fun onComplete() {
                liveDataForViewToObserve.value = SComplete
            }
        }
    }


}
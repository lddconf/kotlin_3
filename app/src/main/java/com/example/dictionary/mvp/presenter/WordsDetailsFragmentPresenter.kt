package com.example.dictionary.mvp.presenter

import com.example.dictionary.mvp.interactors.WordsDetailsInteractor
import com.example.dictionary.mvp.model.data.DataModel
import com.example.dictionary.mvp.model.datasource.DataSourceLocal
import com.example.dictionary.mvp.model.datasource.DataSourceRemote
import com.example.dictionary.mvp.navigation.IDictionaryAppScreens
import com.example.dictionary.mvp.rx.ISchedulerProvider
import com.example.dictionary.mvp.views.IWordsDetailsView
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import geekbrains.ru.translator.model.repository.RepositoryImplementation
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver

class WordsDetailsFragmentPresenter(
    private val router: Router,
    private val screens : IDictionaryAppScreens,
    private val schedulerProvider : ISchedulerProvider,
    private val interactor: WordsDetailsInteractor = WordsDetailsInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    )
) : Presenter<IWordsDetailsView>() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun detachView(view: IWordsDetailsView) {
        super.detachView(view)
        compositeDisposable.clear()
    }

    fun translate(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentView?.showLoading(null) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<List<DataModel>> {
        return object : DisposableObserver<List<DataModel>>() {

            override fun onNext(words: List<DataModel>) {
                currentView?.showWords(words)
            }

            override fun onError(e: Throwable) {
                currentView?.showError(e)
            }

            override fun onComplete() {
            }
        }
    }
}
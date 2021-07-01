package com.example.dictionary.mvp.presenter

import com.example.dictionary.mvp.interactors.WordsDetailsInteractor
import com.example.dictionary.mvp.model.data.DataModel
import com.example.dictionary.mvp.model.datasource.DataSourceLocal
import com.example.dictionary.mvp.model.datasource.DataSourceRemote
import com.example.dictionary.mvp.navigation.IDictionaryAppScreens
import com.example.dictionary.mvp.presenter.list.IWordDetails
import com.example.dictionary.mvp.presenter.list.IWordsDetailsListPresenter
import com.example.dictionary.mvp.rx.ISchedulerProvider
import com.example.dictionary.mvp.views.IWordsDetailsView
import com.example.dictionary.ui.adapters.ResultListRVAdapter
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import geekbrains.ru.translator.model.repository.RepositoryImplementation
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver

class WordsDetailsFragmentPresenter(
    private val router: Router,
    private val screens: IDictionaryAppScreens,
    private val schedulerProvider: ISchedulerProvider,
    private val interactor: WordsDetailsInteractor = WordsDetailsInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    )
) : Presenter<IWordsDetailsView>() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val wordsListPresenter = DictionaryWordDetailsPresenter().also {
        it.itemClickListener = { view ->
            currentView?.showMessage(
                it.words[view.pos].text ?: "Empty"
            )
        }
    }

    class DictionaryWordDetailsPresenter() : IWordsDetailsListPresenter {
        var words = mutableListOf<DataModel>()

        override var itemClickListener: ((ResultListRVAdapter.RecyclerItemViewHolder) -> Unit)? =
            null

        override fun bindView(view: ResultListRVAdapter.RecyclerItemViewHolder) = with(view) {
            val word = words.get(view.pos)
            headerText(word.text ?: "")
            descriptionText(word.meanings?.get(0)?.translation?.translation ?: "")
        }

        override fun getCount(): Int = words.size
    }


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
                wordsListPresenter.words.clear()
                wordsListPresenter.words.addAll(words)
                currentView?.wordsListChanged(words.size)
            }

            override fun onError(e: Throwable) {
                wordsListPresenter.words.clear()
                currentView?.showError(e)
            }

            override fun onComplete() {
            }
        }
    }
}
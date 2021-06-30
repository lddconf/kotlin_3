package com.example.dictionary.mvp.interactors

import com.example.dictionary.mvp.model.data.DataModel
import com.example.dictionary.mvp.model.repository.Repository
import com.example.dictionary.mvp.presenter.Interactor
import io.reactivex.rxjava3.core.Observable

class WordsDetailsInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<List<DataModel>> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<List<DataModel>> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word)
        } else {
            localRepository.getData(word)
        }
    }
}

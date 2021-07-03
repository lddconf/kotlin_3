package com.example.dictionary.model.interactors

import com.example.dictionary.model.model.data.DataModel
import com.example.dictionary.model.model.repository.Repository
import geekbrains.ru.translator.di.NAME_LOCAL
import geekbrains.ru.translator.di.NAME_REMOTE
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named

class WordsDetailsInteractor @Inject constructor(
    @Named(NAME_REMOTE) val remoteRepository: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val localRepository: Repository<List<DataModel>>
) : Interactor<List<DataModel>> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<List<DataModel>> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word)
        } else {
            localRepository.getData(word)
        }
    }
}

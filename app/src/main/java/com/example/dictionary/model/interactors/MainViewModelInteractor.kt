package com.example.dictionary.model.interactors

import com.example.dictionary.di.NAME_LOCAL
import com.example.dictionary.di.NAME_REMOTE
import com.example.dictionary.model.model.data.DataModel
import com.example.dictionary.model.model.data.SComplete
import com.example.dictionary.model.model.data.settings.SettingsHolder
import com.example.dictionary.model.model.repository.Repository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named

class MainViewModelInteractor @Inject constructor(
    @Named(NAME_REMOTE) val remoteRepository: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val localRepository: Repository<List<DataModel>>
) : Interactor<SettingsHolder> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<SettingsHolder> =
        Observable.fromCompletable { SComplete }
}

package com.example.dictionary.model.interactors

import com.example.dictionary.model.model.data.settings.SettingsHolder
import io.reactivex.rxjava3.core.Observable

class SettingsInteractor (
) : Interactor<SettingsHolder> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<SettingsHolder> =
        Observable.just(SettingsHolder)
}

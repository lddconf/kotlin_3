package com.example.dictionary.model.model.datasource

import com.example.dictionary.model.model.data.DataModel
import io.reactivex.rxjava3.core.Observable

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}

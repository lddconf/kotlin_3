package com.example.dictionary.model.model.datasource


import com.example.dictionary.model.model.data.DataModel
import io.reactivex.rxjava3.core.Observable


class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}

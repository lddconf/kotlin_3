package com.example.dictionary.model.model.datasource

import com.example.dictionary.model.model.data.DataModel
import io.reactivex.rxjava3.core.Observable


class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

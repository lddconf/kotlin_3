package geekbrains.ru.translator.model.repository

import com.example.dictionary.mvp.model.data.DataModel
import com.example.dictionary.mvp.model.repository.Repository

import com.example.dictionary.mvp.model.datasource.DataSource
import io.reactivex.rxjava3.core.Observable


class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}

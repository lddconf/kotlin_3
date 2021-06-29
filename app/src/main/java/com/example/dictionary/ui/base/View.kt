package geekbrains.ru.translator.view.base

import com.example.dictionary.mvp.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}

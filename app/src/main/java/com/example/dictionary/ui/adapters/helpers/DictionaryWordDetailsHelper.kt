package com.example.dictionary.ui.adapters.helpers

import com.example.dictionary.model.model.data.DataModel
import com.example.dictionary.ui.adapters.ResultListRVAdapter

class DictionaryWordDetailsHelper(
    val words: MutableList<DataModel> = mutableListOf<DataModel>()
) : IWordsDetailsListHelper {
    override var itemClickListener: ((ResultListRVAdapter.RecyclerItemViewHolder) -> Unit)? =
        null

    override fun bindView(view: ResultListRVAdapter.RecyclerItemViewHolder) = with(view) {
        val word = words.get(view.pos)
        headerText(word.text ?: "")
        descriptionText(word.meanings?.get(0)?.translation?.translation ?: "")
    }

    override fun getCount(): Int = words.size
}
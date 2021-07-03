package com.example.dictionary.ui.adapters.helpers

import com.example.dictionary.ui.adapters.ResultListRVAdapter

interface IWordsDetailsListHelper {
    var itemClickListener: ((ResultListRVAdapter.RecyclerItemViewHolder) -> Unit)?
    fun bindView(view: ResultListRVAdapter.RecyclerItemViewHolder)
    fun getCount(): Int
}
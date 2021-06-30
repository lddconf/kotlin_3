package com.example.dictionary.mvp.presenter.list

import com.example.dictionary.ui.adapters.ResultListRVAdapter

interface IWordsDetailsListPresenter {
    var itemClickListener: ((ResultListRVAdapter.RecyclerItemViewHolder) -> Unit)?
    fun bindView(view: ResultListRVAdapter.RecyclerItemViewHolder)
    fun getCount(): Int
}
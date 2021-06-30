package com.example.dictionary.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.databinding.FragmentSearchResultListRvItemBinding
import com.example.dictionary.mvp.model.data.DataModel
import com.example.dictionary.mvp.presenter.list.IWordDetails
import com.example.dictionary.mvp.presenter.list.IWordsDetailsListPresenter

class ResultListRVAdapter(
    val presenter: IWordsDetailsListPresenter
) :
    RecyclerView.Adapter<ResultListRVAdapter.RecyclerItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder =
        RecyclerItemViewHolder(
            FragmentSearchResultListRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        presenter.bindView(holder.apply {
            pos = position
        })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    inner class RecyclerItemViewHolder(val vb: FragmentSearchResultListRvItemBinding) :
        RecyclerView.ViewHolder(vb.root),
        IWordDetails {

        override var pos: Int = -1

        override fun headerText(text: String) = with(vb) {
            headerTextviewRecyclerItem.text = text
        }

        override fun descriptionText(text: String) = with(vb) {
            descriptionTextviewRecyclerItem.text = text
        }
    }
}

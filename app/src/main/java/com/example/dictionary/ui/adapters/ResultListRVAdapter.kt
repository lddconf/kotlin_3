package com.example.dictionary.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.databinding.FragmentSearchResultListRvItemBinding
import com.example.dictionary.model.model.data.DataModel
import com.example.dictionary.ui.adapters.helpers.DictionaryWordDetailsHelper
import com.example.dictionary.ui.adapters.helpers.IWordDetails
import com.example.dictionary.ui.adapters.helpers.IWordsDetailsListHelper

class ResultListRVAdapter(
    val words: MutableList<DataModel> = mutableListOf(),
    private val itemClickListener: ((ResultListRVAdapter.RecyclerItemViewHolder) -> Unit)?,
    private val dataHolder: IWordsDetailsListHelper = DictionaryWordDetailsHelper(words).also {
        it.itemClickListener = itemClickListener
    }
) :
    RecyclerView.Adapter<ResultListRVAdapter.RecyclerItemViewHolder>() {

    fun setData(newWords: List<DataModel>) {
        words.clear()
        words.addAll(newWords)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder =
        RecyclerItemViewHolder(
            FragmentSearchResultListRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                dataHolder.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        dataHolder.bindView(holder.apply {
            pos = position
        })
    }

    override fun getItemCount(): Int {
        return dataHolder.getCount()
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

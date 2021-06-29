package com.example.dictionary.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.databinding.FragmentSearchResultListRvItemBinding
import com.example.dictionary.mvp.model.data.DataModel

class ResultListRVAdapter(private var onListItemClickListener: OnListItemClickListener, private var data: List<DataModel>) :
    RecyclerView.Adapter<ResultListRVAdapter.RecyclerItemViewHolder>() {

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder =
        RecyclerItemViewHolder(
            FragmentSearchResultListRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(val vb: FragmentSearchResultListRvItemBinding) : RecyclerView.ViewHolder(vb.root) {
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                with(vb) {
                    headerTextviewRecyclerItem.text = data.text
                    descriptionTextviewRecyclerItem.text = data.meanings?.get(0)?.translation?.translation
                }
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}

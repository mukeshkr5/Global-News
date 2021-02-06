package com.mkr.globalnews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkr.globalnews.databinding.SearchListItemViewBinding

class SearchListAdapter(
    private val list: Array<String>,
    private val listener: SearchItemSelectListener
) :
    RecyclerView.Adapter<SearchListAdapter.SearchListItemView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListItemView {
        return SearchListItemView(
            SearchListItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchListItemView, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    inner class SearchListItemView(private val binding: SearchListItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(string: String) {
            binding.searchTitle.text = string
            binding.root.setOnClickListener {
                listener.onSearchItemSelected(string)
            }
        }
    }

}

interface SearchItemSelectListener {
    fun onSearchItemSelected(string: String)
}
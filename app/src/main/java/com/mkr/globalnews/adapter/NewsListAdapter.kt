package com.mkr.globalnews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkr.globalnews.R
import com.mkr.globalnews.databinding.PopularNewsItemViewBinding
import com.mkr.globalnews.modal.News
import com.mkr.globalnews.utils.BookmarkList
import com.squareup.picasso.Picasso

class NewsListAdapter(newsList: List<News>, private val listener: NewsSelectionListener) :
    RecyclerView.Adapter<NewsListAdapter.NewsItemViewHolder>() {

    private val list: ArrayList<News> = ArrayList(newsList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder(
            PopularNewsItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.count()

    inner class NewsItemViewHolder(private val binding: PopularNewsItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            with(binding) {
                news.let {
                    newsTitle.text = it.title
                    newsDesc.text = it.description
                    Picasso.get().load(it.urlToImage).into(image)
                    tags.text = it.source.name
                }
                updateBookmarkIcon(news)
                root.setOnClickListener {
                    listener.onNewsSelected(news)
                }
                bookmarkButton.setOnClickListener {
                    listener.onBookMarkClicked(news)
                    updateBookmarkIcon(news)
                }
            }
        }

        private fun updateBookmarkIcon(news: News) {
            binding.bookmarkButton.setImageResource(
                if (BookmarkList.isBookmarked(news)) {
                    R.drawable.ic_bookmarked
                } else {
                    R.drawable.ic_bookmark
                }
            )
        }

    }
}

interface NewsSelectionListener {
    fun onNewsSelected(news: News)
    fun onBookMarkClicked(news: News)
}
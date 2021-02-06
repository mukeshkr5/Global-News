package com.mkr.globalnews.presentations.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.mkr.globalnews.R
import com.mkr.globalnews.adapter.NewsListAdapter
import com.mkr.globalnews.adapter.NewsSelectionListener
import com.mkr.globalnews.databinding.ActivityBookMarkBinding
import com.mkr.globalnews.modal.News
import com.mkr.globalnews.utils.BookmarkList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookMarkActivity : AppCompatActivity(), NewsSelectionListener {

    private lateinit var binding: ActivityBookMarkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookMarkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        with(binding) {
            bookmarkList.apply {
                adapter = NewsListAdapter(BookmarkList.getBookmarkList(), this@BookMarkActivity)

                val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                ContextCompat.getDrawable(this@BookMarkActivity, R.drawable.divider_line)?.let {
                    itemDecoration.setDrawable(it)
                }
                addItemDecoration(itemDecoration)
            }
            backButton.setOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun onNewsSelected(news: News) {
        val detailIntent = Intent(this, DetailActivity::class.java)
        detailIntent.putExtra(DetailActivity.PARAM_URL, news.url)
        startActivity(detailIntent)
    }

    override fun onBookMarkClicked(news: News) {
        BookmarkList.addNewsToBookmark(news)
    }
}
package com.mkr.globalnews.presentations.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mkr.globalnews.R
import com.mkr.globalnews.adapter.NewsListAdapter
import com.mkr.globalnews.adapter.NewsSelectionListener
import com.mkr.globalnews.databinding.ActivityMainBinding
import com.mkr.globalnews.modal.News
import com.mkr.globalnews.utils.BookmarkList
import com.mkr.globalnews.viewModel.MainViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NewsSelectionListener {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val recyclerViewOnScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                recyclerView.adapter?.let {
                    if (lastVisibleItem == (it.itemCount - 1)) {
                        loadMoreItems()
                    }
                }
            }
        }

    private fun loadMoreItems() {
        if (viewModel.shouldLoadMoreNews()) {
            binding.loading.visibility = View.VISIBLE
            viewModel.loadMoreNews()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        bindViewModel()
    }

    private fun setUpView() {
        binding.apply {
            bookmarkButton.setOnClickListener {
                handleBookMarkButtonClicked()
            }
            searchButton.setOnClickListener {
                handleSearchButtonClicked()
            }
            popularNewLayout.popularNews.apply {
                val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                ContextCompat.getDrawable(this@MainActivity, R.drawable.divider_line)?.let {
                    itemDecoration.setDrawable(it)
                }
                addItemDecoration(itemDecoration)
                addOnScrollListener(recyclerViewOnScrollListener)
            }
        }
    }

    private fun handleSearchButtonClicked() {
        val detailIntent = Intent(this, SearchActivity::class.java)
        startActivity(detailIntent)
    }

    private fun handleBookMarkButtonClicked() {
        val detailIntent = Intent(this, BookMarkActivity::class.java)
        startActivity(detailIntent)
    }

    private fun bindViewModel() {
        viewModel.fetchNewsList(0)
        viewModel.newsList.observe(this, {
            binding.loading.visibility = View.GONE
            binding.bindTopNews(it.first())
            binding.popularNewLayout.popularNews.apply {
                adapter = NewsListAdapter(it, this@MainActivity)
            }
        })
    }

    private fun ActivityMainBinding.bindTopNews(news: News) {
        with(topNewsLayout) {
            news.let {
                newsTitle.text = it.title
                newsDesc.text = it.description
                Picasso.get().load(it.urlToImage).into(image)
                tags.text = it.source.name
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
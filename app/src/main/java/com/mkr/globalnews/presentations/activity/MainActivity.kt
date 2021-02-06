package com.mkr.globalnews.presentations.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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
            }
            topNewsLayout.bookmarkButton.setOnClickListener {
                topNewsLayout.bookmarkButton.setImageResource(R.drawable.ic_bookmarked)
                viewModel.newsList.value?.first()?.let {
                    BookmarkList.addNewsToBookmark(it)
                }
            }
            val layoutManager = popularNewLayout.popularNews.layoutManager as LinearLayoutManager
            binding.nestedView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
                if (v.getChildAt(v.childCount - 1) != null) {
                    if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight &&
                        scrollY > oldScrollY
                    ) {
                        val visibleItemCount = layoutManager.childCount
                        val totalItemCount = layoutManager.itemCount
                        val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loadMoreItems()
                        }
                    }
                }
            })
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
        viewModel.fetchNewsList(1)
        viewModel.newsList.observe(this, {
            binding.loading.visibility = View.GONE
            binding.bindTopNews(it.first())
            binding.popularNewLayout.popularNews.apply {
                val list = it.toMutableList()
                if (list.size > 0) {
                    list.removeAt(0)
                }
                adapter = NewsListAdapter(list, this@MainActivity)
            }
        })
        viewModel.errorObserver.observe(this, {
            // Handle Error case
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
package com.mkr.globalnews.presentations.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.mkr.globalnews.R
import com.mkr.globalnews.adapter.NewsListAdapter
import com.mkr.globalnews.adapter.NewsSelectionListener
import com.mkr.globalnews.databinding.ActivityDetailBinding
import com.mkr.globalnews.modal.News
import com.mkr.globalnews.utils.BookmarkList
import com.mkr.globalnews.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(), NewsSelectionListener {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var webViewUrl: String

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        webViewUrl = requireNotNull(intent.getStringExtra(PARAM_URL))
        setUpView()
        bindViewModel()
    }

    private fun setUpView() {
        with(binding) {
            updateWebview(webViewUrl)
            webview.webViewClient = WebViewClient()
            backButton.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun updateWebview(url: String) {
        with(binding) {
            webview.loadUrl(url)
            webviewUrl.text = url
        }
    }

    private fun bindViewModel() {
        viewModel.fetchNewsList(1)
        viewModel.newsList.observe(this, {
            binding.popularNewLayout.popularNews.apply {
                adapter = NewsListAdapter(it, this@DetailActivity)

                val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                ContextCompat.getDrawable(this@DetailActivity, R.drawable.divider_line)?.let {
                    itemDecoration.setDrawable(it)
                }
                addItemDecoration(itemDecoration)
            }
        })
    }

    companion object {
        const val PARAM_URL = "param_url"
    }

    override fun onNewsSelected(news: News) {}

    override fun onBookMarkClicked(news: News) {
        BookmarkList.addNewsToBookmark(news)
    }
}
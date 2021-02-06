package com.mkr.globalnews.presentations.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.mkr.globalnews.R
import com.mkr.globalnews.adapter.NewsListAdapter
import com.mkr.globalnews.adapter.NewsSelectionListener
import com.mkr.globalnews.adapter.SearchItemSelectListener
import com.mkr.globalnews.adapter.SearchListAdapter
import com.mkr.globalnews.databinding.ActivitySearchBinding
import com.mkr.globalnews.modal.News
import com.mkr.globalnews.utils.BookmarkList
import com.mkr.globalnews.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), NewsSelectionListener, SearchItemSelectListener {

    private lateinit var binding: ActivitySearchBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        with(binding) {
            searchView.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
            searchIcon.setOnClickListener {
                performSearch()
            }
            backButton.setOnClickListener {
                onBackPressed()
            }
            searchHistoryList.apply {
                val sharedPreferences = getSharedPreferences("GlobalNews", MODE_PRIVATE)
                val set: MutableSet<String>? =
                    sharedPreferences.getStringSet("SEARCH_HISTORY_KEY", emptySet())
                adapter = SearchListAdapter(set?.toTypedArray() ?: emptyArray(), this@SearchActivity)
            }
        }
    }

    private fun performSearch() {
        binding.searchView.clearFocus()
        val inputManager: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(binding.searchView.windowToken, 0)
        viewModel.fetchNewsByCategory(binding.searchView.text.toString()).observe(this, {
            binding.searchHistoryList.visibility = View.GONE
            binding.searchList.apply {
                adapter = NewsListAdapter(it, this@SearchActivity)

                val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                ContextCompat.getDrawable(this@SearchActivity, R.drawable.divider_line)?.let {
                    itemDecoration.setDrawable(it)
                }
                addItemDecoration(itemDecoration)
            }
        })
        saveKeyToSharedPreferences(binding.searchView.text.toString())
    }

    private fun saveKeyToSharedPreferences(key: String) {
        val sharedPreferences = getSharedPreferences("GlobalNews", MODE_PRIVATE)
        val set: MutableSet<String>? =
            sharedPreferences.getStringSet("SEARCH_HISTORY_KEY", emptySet())?.toMutableSet()
        set?.add(key)
        sharedPreferences.edit().putStringSet("SEARCH_HISTORY_KEY", set).apply()
    }

    override fun onNewsSelected(news: News) {
        val detailIntent = Intent(this, DetailActivity::class.java)
        detailIntent.putExtra(DetailActivity.PARAM_URL, news.url)
        startActivity(detailIntent)
    }

    override fun onBookMarkClicked(news: News) {
        BookmarkList.addNewsToBookmark(news)
    }

    override fun onSearchItemSelected(string: String) {
        binding.searchView.setText(string)
        performSearch()
    }
}
package com.mkr.globalnews.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mkr.globalnews.modal.News
import com.mkr.globalnews.repository.NewsRepository
import com.mkr.globalnews.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _newsList: MutableLiveData<List<News>> = MutableLiveData()
    val newsList: LiveData<List<News>> = _newsList

    private var resultCount = 0

    private var page: Int = 0

    fun fetchNewsList(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getNewsList(page)
            if (response.status == Resource.Status.SUCCESS && response.data != null) {
                val updatedList: ArrayList<News> = arrayListOf()
                val previousList = ArrayList(_newsList.value?.toList() ?: arrayListOf())
                updatedList.addAll(previousList)
                updatedList.addAll(response.data.articles)
                _newsList.postValue(updatedList)
                resultCount = response.data.totalResults
            }
        }
    }

    fun fetchNewsByCategory(category: String) = liveData(Dispatchers.IO) {
        val response = repository.getNewsListByCategory(category)
        if (response.status == Resource.Status.SUCCESS && response.data != null) {
            emit(response.data.articles)
        }
    }

    fun shouldLoadMoreNews(): Boolean {
        newsList.value?.let {
            return it.size < resultCount
        } ?: kotlin.run {
            return false
        }
    }

    fun loadMoreNews() {
        fetchNewsList(++page)
    }
}
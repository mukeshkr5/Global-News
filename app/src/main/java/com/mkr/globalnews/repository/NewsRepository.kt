package com.mkr.globalnews.repository

import com.mkr.globalnews.modal.NewsResponse
import com.mkr.globalnews.utils.Resource

interface NewsRepository {

    suspend fun getNewsList(page: Int, country: String = "IN"): Resource<NewsResponse>

    suspend fun getNewsListByCategory(category: String, country: String = "IN"): Resource<NewsResponse>
}
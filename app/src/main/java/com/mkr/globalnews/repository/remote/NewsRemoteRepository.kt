package com.mkr.globalnews.repository.remote

import com.mkr.globalnews.modal.NewsResponse
import com.mkr.globalnews.repository.NewsRepository
import com.mkr.globalnews.utils.Resource
import javax.inject.Inject

class NewsRemoteRepository @Inject constructor(
    private val service: NewsRemoteService
) : BaseDataSource(), NewsRepository {

    override suspend fun getNewsList(page: Int, country: String) =
        getResult { service.getNewsList(page, country) }

    override suspend fun getNewsListByCategory(
        category: String,
        country: String
    ): Resource<NewsResponse> =
        getResult { service.getNewsByCategory(category) }
}
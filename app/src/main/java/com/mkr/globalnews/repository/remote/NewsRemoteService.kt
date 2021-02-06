package com.mkr.globalnews.repository.remote

import com.mkr.globalnews.BuildConfig
import com.mkr.globalnews.modal.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsRemoteService {

    @GET("v2/top-headlines?&apiKey=${BuildConfig.API_KEY}&pageSize=10")
    suspend fun getNewsList(
        @Query("page") page: Int,
        @Query("country") country: String,
    ): Response<NewsResponse>

    @GET("v2/everything?apiKey=${BuildConfig.API_KEY}")
    suspend fun getNewsByCategory(
        @Query("q") category: String
    ): Response<NewsResponse>
}
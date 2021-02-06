package com.mkr.globalnews.modal

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<News>
)
package com.mkr.globalnews.utils

import com.mkr.globalnews.modal.News

object BookmarkList {
    private val list = ArrayList<News>()

    fun addNewsToBookmark(news: News) {
        list.add(news)
    }

    fun getBookmarkList(): List<News> = list

    fun isBookmarked(news: News): Boolean {
        // TODO Updated this logic to appropriate one.
        return list.any { it.title == news.title }
    }
}
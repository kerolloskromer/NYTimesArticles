package com.kromer.nytimes.domain.repository

import com.kromer.nytimes.domain.model.Article

/**
 * Main entry point for accessing articles data.
 */
interface ArticlesDataSource {
    suspend fun getPopularArticles(period: Int): List<Article>
    suspend fun add(articles: List<Article>)
    suspend fun get(id: Long): Article?
}
package com.kromer.nytimes.domain.repository

import com.kromer.nytimes.domain.model.Article

/**
 * Interface to the data layer.
 */
interface ArticlesRepository {
    suspend fun getPopularArticles(period: Int, forceUpdate: Boolean): List<Article>
    suspend fun add(articles: List<Article>)
    suspend fun get(id: Long): Article?
}
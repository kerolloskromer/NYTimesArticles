package com.kromer.nytimes.data.repository

import com.kromer.nytimes.data.source.local.ArticlesLocalDataSource
import com.kromer.nytimes.data.source.remote.ArticlesRemoteDataSource
import com.kromer.nytimes.domain.model.Article
import com.kromer.nytimes.domain.repository.ArticlesRepository
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val articlesLocalDataSource: ArticlesLocalDataSource,
    private val articlesRemoteDataSource: ArticlesRemoteDataSource
) : ArticlesRepository {
    override suspend fun getPopularArticles(period: Int, forceUpdate: Boolean): List<Article> {
        return if (forceUpdate) {
            val items = articlesRemoteDataSource.getPopularArticles(period)
            add(items)
            items
        } else {
            articlesLocalDataSource.getPopularArticles(period)
        }
    }

    override suspend fun add(articles: List<Article>) = articlesLocalDataSource.add(articles)

    override suspend fun get(id: Long): Article? = articlesLocalDataSource.get(id)
}
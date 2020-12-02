package com.kromer.nytimes.data.source.local

import com.kromer.nytimes.domain.model.Article
import com.kromer.nytimes.domain.repository.ArticlesDataSource
import javax.inject.Inject

class ArticlesLocalDataSource @Inject constructor(
    private val articlesDao: ArticlesDao
) :
    ArticlesDataSource {
    override suspend fun getPopularArticles(period: Int): List<Article> = articlesDao.get()

    override suspend fun add(articles: List<Article>) = articlesDao.insert(articles)

    override suspend fun get(id: Long): Article? = articlesDao.get(id)
}
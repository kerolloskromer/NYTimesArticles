package com.kromer.nytimes.data.source.remote

import com.kromer.nytimes.domain.model.Article
import com.kromer.nytimes.domain.repository.ArticlesDataSource
import javax.inject.Inject

class ArticlesRemoteDataSource @Inject constructor(
    private val apiInterface: ArticlesApiInterface
) :
    ArticlesDataSource {
    override suspend fun getPopularArticles(period: Int): List<Article> =
        apiInterface.getPopularArticles(period).results

    override suspend fun add(articles: List<Article>) {
        TODO("Not yet implemented")
    }

    override suspend fun get(id: Long): Article? {
        TODO("Not yet implemented")
    }
}
package com.kromer.nytimes.domain.interactor

import com.kromer.nytimes.domain.model.Article
import com.kromer.nytimes.domain.repository.ArticlesRepository
import javax.inject.Inject

class GetPopularArticlesInteractor @Inject constructor(
    private val repository: ArticlesRepository
) {
    suspend fun getPopularArticles(
        period: Int,
        forceUpdate: Boolean
    ): List<Article> {
        return repository.getPopularArticles(period, forceUpdate)
    }
}
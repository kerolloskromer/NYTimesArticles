package com.kromer.nytimes.domain.interactor

import com.kromer.nytimes.domain.model.Article
import com.kromer.nytimes.domain.repository.ArticlesRepository
import javax.inject.Inject

class AddArticlesInteractor @Inject constructor(
    private val repository: ArticlesRepository
) {
    suspend fun add(articles: List<Article>) = repository.add(articles)
}
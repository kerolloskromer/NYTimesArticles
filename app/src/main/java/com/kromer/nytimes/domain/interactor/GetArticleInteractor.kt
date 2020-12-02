package com.kromer.nytimes.domain.interactor

import com.kromer.nytimes.domain.model.Article
import com.kromer.nytimes.domain.repository.ArticlesRepository
import javax.inject.Inject

class GetArticleInteractor @Inject constructor(
    private val repository: ArticlesRepository
) {
    suspend fun getArticle(id: Long): Article? = repository.get(id)
}
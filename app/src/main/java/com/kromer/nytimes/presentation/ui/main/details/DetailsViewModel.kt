package com.kromer.nytimes.presentation.ui.main.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kromer.nytimes.domain.interactor.GetArticleInteractor
import com.kromer.nytimes.domain.model.Article
import com.kromer.nytimes.presentation.base.BaseViewModel
import com.kromer.nytimes.utils.Resource
import kotlinx.coroutines.launch

class DetailsViewModel @ViewModelInject constructor(
    private val getArticleInteractor: GetArticleInteractor
) : BaseViewModel() {

    private val _article = MutableLiveData<Resource<Article>>()
    val article: LiveData<Resource<Article>> = _article

    fun getArticle(id: Long) {
        viewModelScope.launch {
            _article.value = Resource.loading(data = null)
            try {
                _article.value =
                    Resource.success(
                        data = getArticleInteractor.getArticle(id)!!
                    )
            } catch (exception: Exception) {
                _article.value =
                    Resource.error(data = null, message = exception.message ?: "Error Occurred!")
            }
        }
    }
}
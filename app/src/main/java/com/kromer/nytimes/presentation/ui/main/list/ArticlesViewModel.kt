package com.kromer.nytimes.presentation.ui.main.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kromer.nytimes.domain.interactor.GetPopularArticlesInteractor
import com.kromer.nytimes.domain.model.Article
import com.kromer.nytimes.presentation.base.BaseViewModel
import com.kromer.nytimes.utils.Resource
import kotlinx.coroutines.launch

class ArticlesViewModel @ViewModelInject constructor(
    private val getPopularArticlesInteractor: GetPopularArticlesInteractor,
) : BaseViewModel() {

    private val _popularArticles = MutableLiveData<Resource<List<Article>>>()
    val popularArticles: LiveData<Resource<List<Article>>> = _popularArticles

    fun getPopularArticles(period: Int, forceUpdate: Boolean) {
        viewModelScope.launch {
            _popularArticles.value = Resource.loading(data = null)
            try {
                _popularArticles.value =
                    Resource.success(
                        data = getPopularArticlesInteractor.getPopularArticles(
                            period,
                            forceUpdate
                        )
                    )
            } catch (exception: Exception) {
                _popularArticles.value =
                    Resource.error(data = null, message = exception.message ?: "Error Occurred!")
            }
        }
    }
}
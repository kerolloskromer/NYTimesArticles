package com.kromer.nytimes.data.source.remote

import com.google.gson.annotations.SerializedName
import com.kromer.nytimes.domain.model.Article

data class ArticlesResponse(
    @SerializedName("results")
    val results: List<Article>
)
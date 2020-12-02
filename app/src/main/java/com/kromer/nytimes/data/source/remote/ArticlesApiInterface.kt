package com.kromer.nytimes.data.source.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ArticlesApiInterface {

    @GET("viewed/{period}.json")
    suspend fun getPopularArticles(@Path("period") period: Int): ArticlesResponse
}
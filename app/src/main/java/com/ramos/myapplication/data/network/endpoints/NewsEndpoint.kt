package com.ramos.myapplication.data.network.endpoints

import com.ramos.myapplication.data.network.entities.allNews.AllNews
import com.ramos.myapplication.data.network.entities.topNews.NewsApi
import retrofit2.Response
import retrofit2.http.GET

interface NewsEndpoint {

    @GET("top")
    suspend fun getAllTopNews(): Response<NewsApi>

    @GET("all")
    suspend fun getAllNews(): Response<AllNews>

}
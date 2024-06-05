package com.ramos.myapplication.data.network.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBase {

    fun returnBaseRetrofitNews(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.thenewsapi.com/v1/news/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(apiClient())
            .build()
    }

    private fun apiClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(NewsInterceptor(ApiKeys.API_NEWS))
        .build()


}
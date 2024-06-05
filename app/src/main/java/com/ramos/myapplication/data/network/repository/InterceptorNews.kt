package com.ramos.myapplication.data.network.repository

import okhttp3.Interceptor
import okhttp3.Response

class InterceptorNews(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("api_token", apiKey)
            .addQueryParameter("limit", "1")
            .addQueryParameter("locale", "ec")
            .build()

        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)

    }
}
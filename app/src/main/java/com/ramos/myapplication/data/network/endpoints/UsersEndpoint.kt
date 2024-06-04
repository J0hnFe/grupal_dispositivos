package com.ramos.myapplication.data.network.endpoints

import com.ramos.myapplication.data.network.entities.UsersApi
import retrofit2.Response
import retrofit2.http.GET

interface UsersEndpoint {

    @GET("users")
    suspend fun getAllUsers(): Response<List<UsersApi>>


}
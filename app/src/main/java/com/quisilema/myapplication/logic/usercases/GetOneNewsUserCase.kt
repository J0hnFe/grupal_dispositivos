package com.quisilema.myapplication.logic.usercases

import com.quisilema.myapplication.data.network.endpoints.NewsEndpoint
import com.quisilema.myapplication.data.network.endpoints.UUIDNews
import com.quisilema.myapplication.data.network.entities.oneNews.OneNewsDataClass
import com.quisilema.myapplication.data.network.entities.topNews.Data
import com.quisilema.myapplication.data.network.repository.RetrofitBase
import com.quisilema.myapplication.ui.entities.NewsDataUI

import com.quisilema.myapplication.ui.entities.toNewsDataUI
class GetOneNewsUserCase {


    suspend operator fun invoke(uuid:String): Result<OneNewsDataClass?> {
        
        var response = RetrofitBase.returnBaseRetrofitNews()
            .create(UUIDNews::class.java)
            .getUUIDNews(uuid)
        return if (response.isSuccessful) {
           val x=  response.body()
            Result.success(x)
        } else {
            Result.failure(Exception("Ocurrio un error en la API"))
        }
    }
}
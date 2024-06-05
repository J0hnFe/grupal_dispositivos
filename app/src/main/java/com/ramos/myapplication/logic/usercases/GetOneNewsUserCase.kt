package com.ramos.myapplication.logic.usercases

import com.ramos.myapplication.data.network.endpoints.NewsEndpoint
import com.ramos.myapplication.data.network.endpoints.UUIDNews
import com.ramos.myapplication.data.network.entities.oneNews.OneNewsDataClass
import com.ramos.myapplication.data.network.repository.RetrofitBase
import com.ramos.myapplication.ui.core.toNewsDataUI
import com.ramos.myapplication.ui.entities.NewsDataUI

class GetOneNewsUserCase {
    suspend operator fun invoke(uuid: String): Result<OneNewsDataClass?> {

        var response = RetrofitBase.returnBaseRetrofitNews()
            .create(UUIDNews::class.java)
            .getUUIDNews(uuid)
        return if (response.isSuccessful) {
            Result.success(response.body())
        } else {
            Result.failure(Exception("Error en la API"))
        }
    }
}
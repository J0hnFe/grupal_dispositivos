package com.ramos.myapplication.logic.usercases

import com.ramos.myapplication.data.network.endpoints.NewsEndpoint
import com.ramos.myapplication.data.network.repository.RetrofitBase
import com.ramos.myapplication.ui.core.toNewsDataUI
import com.ramos.myapplication.ui.entities.NewsDataUI

class GetAllTopsNewUserCase {
    suspend operator fun invoke(): Result<List<NewsDataUI>> {
        var items = ArrayList<NewsDataUI>()

        var response = RetrofitBase.returnBaseRetrofitNews()
            .create(NewsEndpoint::class.java)
            .getAllTopNews()
        return if (response.isSuccessful) {
            response.body()?.data?.forEach {
                items.add(
                    it.toNewsDataUI()
                )
            }
            Result.success(items.toList())
        } else {
            Result.failure(Exception("Error en la API"))
        }
    }
}
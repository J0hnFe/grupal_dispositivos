package com.quisilema.myapplication.logic.usercases

import com.quisilema.myapplication.data.network.endpoints.NewsEndpoint
import com.quisilema.myapplication.data.network.entities.topNews.Data
import com.quisilema.myapplication.data.network.repository.RetrofitBase
import com.quisilema.myapplication.ui.entities.NewsDataUI

import com.quisilema.myapplication.ui.entities.toNewsDataUI
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
            Result.failure(Exception("Ocurrio un error en la API"))
        }
    }
}
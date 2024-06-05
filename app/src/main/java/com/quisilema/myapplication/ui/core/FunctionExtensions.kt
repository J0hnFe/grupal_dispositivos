package com.quisilema.myapplication.ui.core

import android.view.View
import com.quisilema.myapplication.data.network.entities.topNews.Data
import com.quisilema.myapplication.ui.entities.NewsDataUI

class FunctionExtensions {

    fun Data.toNewsDataUI() =
        NewsDataUI(
            this.uuid,
            this.url,
            this.title,
            this.image_url,
            this.description,
            this.language
        )
    }




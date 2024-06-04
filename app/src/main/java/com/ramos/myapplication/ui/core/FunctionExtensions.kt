package com.ramos.myapplication.ui.core

import com.ramos.myapplication.data.network.entities.topNews.Data
import com.ramos.myapplication.ui.entities.NewsDataUI

class FunctionExtensions
fun Data.toNewsDataUI(): NewsDataUI =
    NewsDataUI(
        this.uuid,
        this.url,
        this.title,
        this.image_url,
        this.description,
        this.language
    )

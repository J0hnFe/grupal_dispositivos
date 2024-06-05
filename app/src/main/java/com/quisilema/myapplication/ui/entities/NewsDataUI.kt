package com.quisilema.myapplication.ui.entities

import android.media.audiofx.AudioEffect.Descriptor

import com.quisilema.myapplication.data.network.entities.topNews.Data
import java.util.Date

data class NewsDataUI(
    val id: String,
    var url: String,
    var name: String,
    var image: String,
    val description: String,
    val languague: String

)


fun Data.toNewsDataUI(): NewsDataUI {
    return NewsDataUI(
        this.uuid,
        this.url,
        this.title,
        this.image_url,
        this.description,
        this.language
    )
}
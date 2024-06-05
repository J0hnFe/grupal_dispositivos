package com.ramos.myapplication.ui.entities

import com.ramos.myapplication.data.network.entities.topNews.Data
import org.intellij.lang.annotations.Language

data class NewsDataUI (
    val id: String,
    val url: String,
    val name: String,
    val image: String,
    val description: String,
    val language: String
)


package com.ramos.myapplication.data.network.entities.allNews

data class Data(
    val categories: List<String>,
    val description: String,
    val image_url: String,
    val keywords: String,
    val language: String,
    val published_at: String,
    val relevance_score: Any,
    val snippet: String,
    val source: String,
    val title: String,
    val url: String,
    val uuid: String
)
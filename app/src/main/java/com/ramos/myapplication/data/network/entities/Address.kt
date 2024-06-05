package com.ramos.myapplication.data.network.entities

data class Address(
    val city: String?,
    val geo: Geo?,
    val street: String?,
    val suite: String?,
    val zipcode: String?
)
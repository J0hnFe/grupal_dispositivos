package com.ramos.myapplication.data.local

import android.util.Log

data class UserClass(
    var id: Int,
    var name: String,
    var pass: String,
) {
    init {
        Log.d("DATA", "Se contruye una nueva instacia de Usuarios")
    }
}
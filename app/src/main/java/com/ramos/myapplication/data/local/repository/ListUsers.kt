package com.ramos.myapplication.data.local.repository

import com.ramos.myapplication.data.local.UserClass


class ListUsers {

    private var listUsers = listOf(
        UserClass(1, "admin", "admin"),
        UserClass(2, "fernan", "toji"),
        UserClass(3, "juan", "perez")
    )


    fun checkUserPassword(user: String, pass: String): UserClass? {
        var usReturn: UserClass? = null
        listUsers.forEach { us ->
            if (us.name == user && us.pass == pass) {
                usReturn = us
            }
        }
        return usReturn
    }

    fun checkUserPassword2(user: String, pass: String): Result<UserClass> {
        return try {
            Result.success(listUsers.first { us ->
                us.name == user && us.pass == pass
            })
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}
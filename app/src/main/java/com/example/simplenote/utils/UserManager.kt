package com.example.simplenote.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.simplenote.data.User

object UserManager {
    private const val USER_PREF = "user_prefs"
    private const val USER_NAME = "user_name"
    var userName: String? = null
    var isLogin: Boolean = false
    private val sharedPreferences: SharedPreferences? =
        appContext().getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)

    fun readData() {
        sharedPreferences?.let {
            userName = it.getString(USER_NAME, "")
        }
        isLogin = !userName.isNullOrEmpty()
    }

    fun writeData(user: User) {
        sharedPreferences?.edit()?.let {
            userName = user.userName
            it.putString(USER_NAME, userName)
            it.apply()
        }
    }
}
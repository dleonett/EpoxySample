package com.leonett.photofeed.data.source

import android.content.SharedPreferences
import javax.inject.Inject

class UsersLocalSource @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun storeLocalUser(username: String) {
        sharedPreferences.edit().putString(KEY_USERNAME, username).apply()
    }

    fun getLocalUser(): String? {
        return sharedPreferences.getString(KEY_USERNAME, null)
    }

    fun clearLocalUser() {
        sharedPreferences.edit().remove(KEY_USERNAME).apply()
    }

    companion object {
        const val KEY_USERNAME = "KEY_USERNAME"
    }

}

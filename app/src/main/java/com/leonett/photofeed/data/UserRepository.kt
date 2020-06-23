package com.leonett.photofeed.data

import com.leonett.photofeed.data.source.UsersLocalSource
import javax.inject.Inject

class UserRepository @Inject constructor(private val usersLocalSource: UsersLocalSource) {

    fun getLocalUser(): String? {
        return usersLocalSource.getLocalUser()
    }

    fun storeLocalUser(username: String) {
        usersLocalSource.storeLocalUser(username)
    }

    fun clearLocalUser() {
        usersLocalSource.clearLocalUser()
    }

}
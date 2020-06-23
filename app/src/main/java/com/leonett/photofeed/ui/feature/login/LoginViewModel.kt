package com.leonett.photofeed.ui.feature.login

import androidx.lifecycle.ViewModel
import com.leonett.photofeed.data.UserRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    fun login(username: String, password: String) {
        userRepository.storeLocalUser(username)
    }

}
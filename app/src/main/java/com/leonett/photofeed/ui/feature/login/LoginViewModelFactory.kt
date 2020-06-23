package com.leonett.photofeed.ui.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leonett.photofeed.data.UserRepository
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(userRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }

}
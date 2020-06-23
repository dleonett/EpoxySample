package com.leonett.photofeed.ui.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leonett.photofeed.data.UserRepository
import javax.inject.Inject

class SplashViewModelFactory @Inject constructor(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            SplashViewModel(userRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }

}
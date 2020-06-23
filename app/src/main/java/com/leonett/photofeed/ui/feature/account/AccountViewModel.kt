package com.leonett.photofeed.ui.feature.account

import androidx.lifecycle.ViewModel
import com.leonett.photofeed.data.UserRepository
import javax.inject.Inject

class AccountViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    fun logoutUser() {
        userRepository.clearLocalUser()
    }

}
package com.leonett.photofeed.ui.feature.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leonett.photofeed.data.PostsRepository
import com.leonett.photofeed.data.UserRepository
import javax.inject.Inject

class AccountViewModelFactory @Inject constructor(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            AccountViewModel(userRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }

}
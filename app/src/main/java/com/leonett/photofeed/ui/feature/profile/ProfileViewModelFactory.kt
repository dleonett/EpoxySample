package com.leonett.photofeed.ui.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leonett.photofeed.data.PostsRepository
import javax.inject.Inject

class ProfileViewModelFactory @Inject constructor(private val postsRepository: PostsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            ProfileViewModel(postsRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }

}
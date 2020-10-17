package com.leonett.photofeed.ui.feature.detail.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leonett.photofeed.data.PostsRepository
import javax.inject.Inject

class PostDetailViewModelFactory @Inject constructor(private val postsRepository: PostsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PostDetailViewModel::class.java)) {
            PostDetailViewModel(postsRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }

}
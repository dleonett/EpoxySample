package com.leonett.photofeed.ui.feature.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leonett.photofeed.data.PostsRepository
import javax.inject.Inject

class FeedViewModelFactory @Inject constructor(private val postsRepository: PostsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            FeedViewModel(postsRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }

}
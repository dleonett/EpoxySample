package com.leonett.photofeed.ui.feature.detail.post

import com.leonett.photofeed.data.model.Post

sealed class PostDetailScreenState {
    data class Loading(val post: Post? = null) : PostDetailScreenState()
    data class Success(val post: Post?) : PostDetailScreenState()
    data class Error(val message: String) : PostDetailScreenState()
}
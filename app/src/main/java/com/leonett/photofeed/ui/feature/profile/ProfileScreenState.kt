package com.leonett.photofeed.ui.feature.profile

import com.leonett.photofeed.data.model.UserPostsWrapper

sealed class ProfileScreenState {
    data class Loading(val userPostsWrapper: UserPostsWrapper?) :
        ProfileScreenState()

    data class Success(val userPostsWrapper: UserPostsWrapper?) :
        ProfileScreenState()

    data class Error(
        val userPostsWrapper: UserPostsWrapper?,
        val message: String
    ) :
        ProfileScreenState()
}
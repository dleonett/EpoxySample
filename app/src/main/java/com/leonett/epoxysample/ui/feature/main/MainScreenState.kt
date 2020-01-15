package com.leonett.epoxysample.ui.feature.main

import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.data.model.Story

sealed class MainScreenState {
    data class Loading(val titleResId: Int, val stories: List<Story>, val posts: List<Post>, val loadMore: Boolean) : MainScreenState()
    data class Success(val titleResId: Int, val stories: List<Story>, val posts: List<Post>, val loadMore: Boolean) : MainScreenState()
    data class Error(val titleResId: Int, val stories: List<Story>, val posts: List<Post>, val loadMore: Boolean) : MainScreenState()
}
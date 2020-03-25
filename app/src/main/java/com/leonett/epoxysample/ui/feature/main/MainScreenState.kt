package com.leonett.epoxysample.ui.feature.main

import com.leonett.epoxysample.data.model.HomeData
import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.data.model.Story

sealed class MainScreenState {
    data class Loading(val homeData: HomeData, val loadMore: Boolean) : MainScreenState()
    data class Success(val homeData: HomeData, val loadMore: Boolean) : MainScreenState()
    data class Error(val homeData: HomeData, val loadMore: Boolean) : MainScreenState()
}
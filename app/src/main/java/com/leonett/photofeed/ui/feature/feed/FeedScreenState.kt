package com.leonett.photofeed.ui.feature.feed

import com.leonett.photofeed.ui.viewobject.FeedScreenData

sealed class FeedScreenState {
    object Idle : FeedScreenState()

    data class Loading(val feedScreenData: FeedScreenData, val loadMore: Boolean) :
        FeedScreenState()

    data class Success(val feedScreenData: FeedScreenData, val loadMore: Boolean) :
        FeedScreenState()

    data class Error(
        val feedScreenData: FeedScreenData,
        val message: String?,
        val loadMore: Boolean
    ) :
        FeedScreenState()
}
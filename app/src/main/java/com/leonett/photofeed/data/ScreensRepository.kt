package com.leonett.photofeed.data

import com.leonett.photofeed.data.mapper.ComposableScreen
import com.leonett.photofeed.data.source.ScreensRemoteSource
import javax.inject.Inject

class ScreensRepository @Inject constructor(private val screensRemoteSource: ScreensRemoteSource) {

    suspend fun getScreen(id: Int): ComposableScreen {
        return screensRemoteSource.fetchScreen(id)
    }

}
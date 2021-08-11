package com.leonett.photofeed.data.source

import com.leonett.photofeed.data.mapper.ComposableScreen
import retrofit2.http.GET
import javax.inject.Inject

class ScreensRemoteSource @Inject constructor(private val screensApiService: ScreensApiService) {

    suspend fun fetchScreen(id: Int) = screensApiService.fetchScreen()

}

interface ScreensApiService {

    @GET("v2/layout/mp_multiplayer_hub")
    suspend fun fetchScreen(): ComposableScreen

}
package com.leonett.photofeed.data.source

import com.leonett.photofeed.data.mapper.ComposableScreen
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

class ScreensRemoteSource @Inject constructor(private val screensApiService: ScreensApiService) {

    suspend fun fetchScreen(id: Int) = screensApiService.fetchScreen(id)

}

interface ScreensApiService {

    @GET("screen{id}")
    suspend fun fetchScreen(@Path("id") id: Int): ComposableScreen

}
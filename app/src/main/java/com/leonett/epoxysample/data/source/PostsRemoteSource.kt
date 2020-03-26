package com.leonett.epoxysample.data.source

import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.data.model.Story
import retrofit2.http.GET
import javax.inject.Inject

class PostsRemoteSource @Inject constructor(private val postsApiService: PostsApiService) {

    suspend fun fetchStories() = postsApiService.fetchStories()

    suspend fun fetchPosts() = postsApiService.fetchPosts()

}

interface PostsApiService {

    @GET("v2/5e7befc82d0000610011aad9")
    suspend fun fetchStories(): List<Story>

    @GET("v2/5e7aa8803000000878930d2c")
    suspend fun fetchPosts(): List<Post>

}
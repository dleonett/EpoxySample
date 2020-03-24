package com.leonett.epoxysample.data.source

import com.leonett.epoxysample.data.model.Post
import retrofit2.http.GET
import javax.inject.Inject

class PostsRemoteSource @Inject constructor(private val postsApiService: PostsApiService) {

    suspend fun fetchPosts(): List<Post> {
        return postsApiService.fetchPosts()
    }

}

interface PostsApiService {

    @GET("v2/5e79a09b2d0000f72c18be45")
    suspend fun fetchPosts(): List<Post>

}
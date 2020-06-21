package com.leonett.photofeed.data.source

import com.leonett.photofeed.data.model.Post
import com.leonett.photofeed.data.model.Story
import com.leonett.photofeed.data.model.instagram.IgResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

class PostsRemoteSource @Inject constructor(private val postsApiService: PostsApiService) {

    suspend fun fetchStories() = postsApiService.fetchStories()

    suspend fun fetchPosts() = postsApiService.fetchPosts()

    suspend fun fetchMorePosts() = postsApiService.fetchMorePosts()

    suspend fun fetchUserProfile(username: String) =
        postsApiService.fetchUserProfile(username, "1")

}

interface PostsApiService {

    @GET("v2/5e7befc82d0000610011aad9")
    suspend fun fetchStories(): List<Story>

    @GET("v2/5e7aa8803000000878930d2c")
    suspend fun fetchPosts(): List<Post>

    @GET("v2/5e815952300000bd386f96f0")
    suspend fun fetchMorePosts(): List<Post>

    @GET("https://www.instagram.com/{username}")
    suspend fun fetchUserProfile(
        @Path("username") username: String,
        @Query("__a") param: String
    ): IgResponse

}
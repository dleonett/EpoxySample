package com.leonett.epoxysample.data

import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.data.model.PostsStoriesWrapper
import com.leonett.epoxysample.data.model.Story
import com.leonett.epoxysample.data.source.PostsLocalSource
import com.leonett.epoxysample.data.source.PostsRemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val postsLocalSource: PostsLocalSource,
    private val postsRemoteSource: PostsRemoteSource
) {

    suspend fun fetchRemoteStories(): List<Story> {
        val stories = postsRemoteSource.fetchStories()
        postsLocalSource.insertStories(stories)
        return stories
    }

    suspend fun fetchRemotePosts(): List<Post> {
        val posts = postsRemoteSource.fetchPosts()
        postsLocalSource.insertPosts(posts)
        return posts
    }

    suspend fun fetchMoreRemotePosts(): List<Post> {
        val posts = postsRemoteSource.fetchMorePosts()
        postsLocalSource.insertPosts(posts)
        return posts
    }

    fun getPostAndStoriesObservable(): Flow<PostsStoriesWrapper> {
        return postsLocalSource.getPostsObservable()
            .combine(postsLocalSource.getStoriesObservable()) { posts, stories ->
                PostsStoriesWrapper(posts, stories)
            }
    }

}
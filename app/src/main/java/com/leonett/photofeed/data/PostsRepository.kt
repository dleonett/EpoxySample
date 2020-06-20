package com.leonett.photofeed.data

import com.leonett.photofeed.data.model.Post
import com.leonett.photofeed.data.model.PostsStoriesWrapper
import com.leonett.photofeed.data.model.Story
import com.leonett.photofeed.data.model.User
import com.leonett.photofeed.data.source.PostsLocalSource
import com.leonett.photofeed.data.source.PostsRemoteSource
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

    fun getUserObservable(userId: Int): Flow<User> {
        return postsLocalSource.getUserObservable(userId)
    }

    fun getPostsByUserObservable(userId: Int): Flow<List<Post>> {
        return postsLocalSource.getPostsByUserObservable(userId)
    }

    suspend fun populateData() {
        postsLocalSource.insertPosts(Post.generateDummyList())
        postsLocalSource.insertStories(Story.generateDummyList())
        postsLocalSource.insertUsers(User.generateDummyList())
    }

    suspend fun likePost(post: Post) {
        val likedByMe = !post.likedByMe
        val likes = if (likedByMe) post.likes + 1 else post.likes - 1
        postsLocalSource.updatePostLike(post.id, likedByMe, likes)
    }

    suspend fun forceLikePost(post: Post) {
        if (!post.likedByMe) {
            val likes = post.likes + 1
            postsLocalSource.updatePostLike(post.id, true, likes)
        }
    }

}
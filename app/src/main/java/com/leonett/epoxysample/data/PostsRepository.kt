package com.leonett.epoxysample.data

import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.data.model.Story
import com.leonett.epoxysample.data.source.PostsLocalSource
import javax.inject.Inject

class PostsRepository @Inject constructor(private val postsLocalSource: PostsLocalSource) {

    fun getPostsList(): List<Post> {
        return postsLocalSource.getPostsList()
    }

    fun getMorePostsList(): List<Post> {
        return postsLocalSource.getMorePostsList()
    }

    fun getStoriesList(): List<Story> {
        return postsLocalSource.getStoriesList()
    }

}
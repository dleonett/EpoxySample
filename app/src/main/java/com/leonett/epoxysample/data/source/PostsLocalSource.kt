package com.leonett.epoxysample.data.source

import com.leonett.epoxysample.data.model.Post
import javax.inject.Inject

class PostsLocalSource @Inject constructor() {

    fun getPostsList(): List<Post> {
        return Post.generateDummyList()
    }

    fun getMorePostsList(): List<Post> {
        return Post.generateDummySecondList()
    }

}
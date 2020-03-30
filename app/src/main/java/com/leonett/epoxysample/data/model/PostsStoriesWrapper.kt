package com.leonett.epoxysample.data.model

data class PostsStoriesWrapper(
    val posts: List<Post> = ArrayList(),
    val stories: List<Story> = ArrayList()
)

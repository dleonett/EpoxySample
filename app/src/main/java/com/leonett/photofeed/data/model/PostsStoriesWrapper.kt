package com.leonett.photofeed.data.model

data class PostsStoriesWrapper(
    val posts: List<Post> = ArrayList(),
    val stories: List<Story> = ArrayList()
)

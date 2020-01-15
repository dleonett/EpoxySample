package com.leonett.epoxysample.ui.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.PostsRepository
import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.data.model.Story
import javax.inject.Inject

class MainViewModel @Inject constructor(private val postsRepository: PostsRepository) :
    ViewModel() {

    private var stories: ArrayList<Story> = ArrayList()
    private var posts: ArrayList<Post> = ArrayList()
    private var screenStateMutableLiveData: MutableLiveData<MainScreenState> = MutableLiveData()

    init {
        stories.addAll(postsRepository.getStoriesList())
        posts.addAll(postsRepository.getPostsList())

        screenStateMutableLiveData.value =
            MainScreenState.Success(R.string.header_text, stories, posts, true)
    }

    fun getScreenStateLiveData(): LiveData<MainScreenState> {
        return screenStateMutableLiveData
    }

    fun loadMorePosts() {
        posts.addAll(postsRepository.getMorePostsList())

        screenStateMutableLiveData.value =
            MainScreenState.Success(R.string.header_text, stories, posts, false)
    }

}
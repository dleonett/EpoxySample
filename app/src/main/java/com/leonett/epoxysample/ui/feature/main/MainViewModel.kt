package com.leonett.epoxysample.ui.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.Post
import com.leonett.epoxysample.data.Story

class MainViewModel : ViewModel() {

    private var stories: ArrayList<Story> = ArrayList()
    private var posts: ArrayList<Post> = ArrayList()
    private var screenStateMutableLiveData: MutableLiveData<MainScreenState> = MutableLiveData()

    init {
        stories.addAll(Story.generateDummyList())
        posts.addAll(Post.generateDummyList())

        screenStateMutableLiveData.value = MainScreenState.Success(R.string.header_text, stories, posts, true)
    }

    fun getScreenStateLiveData(): LiveData<MainScreenState> {
        return screenStateMutableLiveData
    }

    fun loadMorePosts() {
        posts.addAll(Post.generateDummySecondList())

        screenStateMutableLiveData.value = MainScreenState.Success(R.string.header_text, stories, posts, false)
    }

}
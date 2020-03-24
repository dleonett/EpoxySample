package com.leonett.epoxysample.ui.feature.main

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.PostsRepository
import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.data.model.Story
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val postsRepository: PostsRepository) :
    ViewModel() {

    private var stories: ArrayList<Story> = ArrayList()
    private var posts: ArrayList<Post> = ArrayList()
    private var screenStateMutableLiveData: MutableLiveData<MainScreenState> = MutableLiveData()

    init {
        viewModelScope.launch {
            try {
                screenStateMutableLiveData.value =
                    MainScreenState.Loading(R.string.header_text, stories, posts, false)

                stories.addAll(postsRepository.getStoriesList())
                posts.addAll(postsRepository.fetchPosts())

                screenStateMutableLiveData.value =
                    MainScreenState.Success(R.string.header_text, stories, posts, false)
            } catch (error: Throwable) {
                screenStateMutableLiveData.value =
                    MainScreenState.Error(R.string.header_text, stories, posts, false)
            }
        }
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
package com.leonett.epoxysample.ui.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonett.epoxysample.data.PostsRepository
import com.leonett.epoxysample.data.model.HomeData
import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.data.model.Story
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val postsRepository: PostsRepository) :
    ViewModel() {

    private var posts = ArrayList<Post>()
    private var stories = ArrayList<Story>()
    private var screenStateMutableLiveData: MutableLiveData<MainScreenState> = MutableLiveData()

    init {
        viewModelScope.launch {
            try {
                screenStateMutableLiveData.value =
                    MainScreenState.Loading(
                        HomeData(0, ArrayList(posts), ArrayList(stories)),
                        false
                    )

                stories.addAll(postsRepository.fetchStories())
                posts.addAll(postsRepository.fetchPosts())

                screenStateMutableLiveData.value =
                    MainScreenState.Success(HomeData(0, ArrayList(posts), ArrayList(stories)), true)
            } catch (error: Throwable) {
                screenStateMutableLiveData.value =
                    MainScreenState.Error(HomeData(0, ArrayList(posts), ArrayList(stories)), false)
            }
        }
    }

    fun getScreenStateLiveData(): LiveData<MainScreenState> {
        return screenStateMutableLiveData
    }

    fun loadMorePosts() {
        viewModelScope.launch {
            screenStateMutableLiveData.value =
                MainScreenState.Loading(HomeData(0, ArrayList(posts), ArrayList(stories)), false)

            // TODO: Replace and request more posts from remote
            delay(3_000)

            posts.addAll(postsRepository.getMorePostsList())

            screenStateMutableLiveData.value =
                MainScreenState.Success(HomeData(0, ArrayList(posts), ArrayList(stories)), false)
        }
    }

}
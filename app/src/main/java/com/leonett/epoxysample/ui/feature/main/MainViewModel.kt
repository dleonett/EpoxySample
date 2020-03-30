package com.leonett.epoxysample.ui.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonett.epoxysample.data.PostsRepository
import com.leonett.epoxysample.ui.viewobject.MainScreenData
import com.leonett.epoxysample.data.model.PostsStoriesWrapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val postsRepository: PostsRepository) :
    ViewModel() {

    private var postsStoriesWrapper: PostsStoriesWrapper = PostsStoriesWrapper()
    private var screenStateMutableLiveData: MutableLiveData<MainScreenState> = MutableLiveData()

    init {
        fetchPostsFromRemote()

        observePostsAndStories()
    }

    private fun showLoadingStatus() {
        screenStateMutableLiveData.value =
            MainScreenState.Loading(
                MainScreenData(
                    0,
                    postsStoriesWrapper
                ), false)
    }

    private fun showSuccessStatus() {
        screenStateMutableLiveData.value =
            MainScreenState.Success(
                MainScreenData(
                    0,
                    postsStoriesWrapper
                ),
                postsStoriesWrapper.posts.size < 11
            )
    }

    private fun showErrorStatus(message: String?) {
        screenStateMutableLiveData.value =
            MainScreenState.Error(
                MainScreenData(
                    0,
                    postsStoriesWrapper
                ), message, false)
    }

    private fun fetchPostsFromRemote() {
        viewModelScope.launch {
            try {
                postsRepository.fetchRemoteStories()
                postsRepository.fetchRemotePosts()
            } catch (error: Throwable) {
                showErrorStatus(error.message)
            }
        }
    }

    private fun observePostsAndStories() {
        viewModelScope.launch {
            postsRepository.getPostAndStoriesObservable().collect { result ->
                postsStoriesWrapper = result
                showSuccessStatus()
            }
        }
    }

    fun getScreenStateLiveData(): LiveData<MainScreenState> {
        return screenStateMutableLiveData
    }

    fun loadMorePosts() {
        viewModelScope.launch {
            try {
                postsRepository.fetchMoreRemotePosts()
            } catch (error: Throwable) {
                showErrorStatus(error.message)
            }
        }
    }

}
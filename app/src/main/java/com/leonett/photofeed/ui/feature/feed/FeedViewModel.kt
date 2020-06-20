package com.leonett.photofeed.ui.feature.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonett.photofeed.data.PostsRepository
import com.leonett.photofeed.data.model.Post
import com.leonett.photofeed.data.model.PostsStoriesWrapper
import com.leonett.photofeed.ui.viewobject.FeedScreenData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedViewModel @Inject constructor(private val postsRepository: PostsRepository) :
    ViewModel() {

    private var postsStoriesWrapper: PostsStoriesWrapper = PostsStoriesWrapper()
    private var screenStateMutableLiveData: MutableLiveData<FeedScreenState> = MutableLiveData()

    init {
        observePostsAndStories()

        viewModelScope.launch {
            postsRepository.populateData()
        }
    }

    private fun showLoadingStatus() {
        screenStateMutableLiveData.value =
            FeedScreenState.Loading(FeedScreenData(0, postsStoriesWrapper), false)
    }

    private fun showSuccessStatus() {
        screenStateMutableLiveData.value =
            FeedScreenState.Success(
                FeedScreenData(0, postsStoriesWrapper),
                postsStoriesWrapper.posts.size < 11
            )
    }

    private fun showErrorStatus(message: String?) {
        screenStateMutableLiveData.value =
            FeedScreenState.Error(FeedScreenData(0, postsStoriesWrapper), message, false)
    }

    private fun fetchPostsFromRemote() {
        viewModelScope.launch {
            try {
                // Ignore fetch result here given we are already observing changes in database
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

    fun getScreenStateLiveData(): LiveData<FeedScreenState> {
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

    fun onPostLikeClick(post: Post) {
        viewModelScope.launch {
            postsRepository.likePost(post)
        }
    }

    fun onPostLikeDoubleClick(post: Post) {
        viewModelScope.launch {
            postsRepository.forceLikePost(post)
        }
    }

    fun onPostCommentClick(post: Post) {

    }

    fun onPostShareClick(post: Post) {

    }

}
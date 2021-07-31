package com.leonett.photofeed.ui.feature.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonett.photofeed.data.PostsRepository
import com.leonett.photofeed.data.model.Post
import com.leonett.photofeed.data.model.PostsStoriesWrapper
import com.leonett.photofeed.ui.viewobject.FeedScreenData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedViewModel @Inject constructor(private val postsRepository: PostsRepository) :
    ViewModel() {

    private var postsStoriesWrapper: PostsStoriesWrapper = PostsStoriesWrapper()

    private val _state = MutableStateFlow<FeedScreenState>(FeedScreenState.Idle)
    val state: StateFlow<FeedScreenState>
        get() = _state

    init {
        viewModelScope.launch {
            postsRepository.populateData()
        }

        viewModelScope.launch {
            postsRepository.getPostAndStoriesObservable()
                .distinctUntilChanged()
                .collect { result ->
                    postsStoriesWrapper = result
                    showSuccessStatus()
                }
        }
    }

    private fun showLoadingStatus() {
        _state.value = FeedScreenState.Loading(FeedScreenData(0, postsStoriesWrapper), false)
    }

    private fun showSuccessStatus() {
        _state.value = FeedScreenState.Success(
            FeedScreenData(0, postsStoriesWrapper),
            postsStoriesWrapper.posts.size < 11
        )
    }

    private fun showErrorStatus(message: String?) {
        _state.value = FeedScreenState.Error(FeedScreenData(0, postsStoriesWrapper), message, false)
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
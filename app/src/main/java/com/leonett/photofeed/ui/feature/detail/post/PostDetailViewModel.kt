package com.leonett.photofeed.ui.feature.detail.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonett.photofeed.data.PostsRepository
import com.leonett.photofeed.data.model.Post
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PostDetailViewModel @Inject constructor(private val postsRepository: PostsRepository) :
    ViewModel() {

    private var screenStateLiveData: MutableLiveData<PostDetailScreenState> =
        MutableLiveData()

    fun setArguments(post: Post?) {
        post?.let {
            viewModelScope.launch {
                postsRepository.getPostByIdAsObservable(it.id)
                    .onStart {
                        screenStateLiveData.value = PostDetailScreenState.Loading()
                    }
                    .collect { result ->
                        screenStateLiveData.value = PostDetailScreenState.Success(result)
                    }
            }
        } ?: run {
            screenStateLiveData.value =
                PostDetailScreenState.Error("Something went wrong getting post info")
        }
    }

    fun getScreenStateLiveData(): LiveData<PostDetailScreenState> {
        return screenStateLiveData
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
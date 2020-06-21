package com.leonett.photofeed.ui.feature.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonett.photofeed.data.PostsRepository
import com.leonett.photofeed.data.model.UserPostsWrapper
import com.leonett.photofeed.data.model.instagram.IgUser
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val postsRepository: PostsRepository) :
    ViewModel() {

    private var userPostsMutableLiveData: MutableLiveData<UserPostsWrapper> = MutableLiveData()
    private var userMutableLiveData: MutableLiveData<IgUser> = MutableLiveData()

    fun setArguments(userId: Int?, username: String?) {
        userId?.let {
            viewModelScope.launch {
                postsRepository.getUserObservable(it)
                    .combine(postsRepository.getPostsByUserObservable(it)) { user, posts ->
                        user?.let {
                            UserPostsWrapper(it, posts)
                        }
                    }
                    .collect { result ->
                        userPostsMutableLiveData.value = result
                    }
            }
        }

        username?.let {
            if (it.isNotEmpty()) {
                fetchUserProfile(it)
            }
        }
    }

    private fun fetchUserProfile(username: String) {
        viewModelScope.launch {
            try {
                val userPosts = postsRepository.fetchUserProfile(username)
                userPostsMutableLiveData.value = userPosts
            } catch (error: Throwable) {
                error.printStackTrace()
            }
        }
    }

    fun getUserPostsLiveData(): LiveData<UserPostsWrapper> {
        return userPostsMutableLiveData
    }

    fun getUserLiveData(): LiveData<IgUser> {
        return userMutableLiveData
    }

}
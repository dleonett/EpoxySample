package com.leonett.photofeed.ui.feature.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonett.photofeed.data.PostsRepository
import com.leonett.photofeed.data.model.UserPostsWrapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val postsRepository: PostsRepository) :
    ViewModel() {

    private var profileScreenStateLiveData: MutableLiveData<ProfileScreenState> = MutableLiveData()

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
                        result?.let {
                            profileScreenStateLiveData.value = ProfileScreenState.Success(it)
                        }
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
            profileScreenStateLiveData.value = ProfileScreenState.Loading(null)

            try {
                val userPosts = postsRepository.fetchUserProfile(username)
                profileScreenStateLiveData.value = ProfileScreenState.Success(userPosts)
            } catch (error: Throwable) {
                profileScreenStateLiveData.value =
                    ProfileScreenState.Error(null, "Something went wrong getting user/posts")
                error.printStackTrace()
            }
        }
    }

    fun getProfileScreenStateLiveData(): LiveData<ProfileScreenState> {
        return profileScreenStateLiveData
    }

}
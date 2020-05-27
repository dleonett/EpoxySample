package com.leonett.epoxysample.ui.feature.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonett.epoxysample.data.PostsRepository
import com.leonett.epoxysample.data.model.UserPostsWrapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val postsRepository: PostsRepository) :
    ViewModel() {

    private var userPostsMutableLiveData: MutableLiveData<UserPostsWrapper> = MutableLiveData()

    fun setArguments(userId: Int) {
        viewModelScope.launch {
            postsRepository.getUserObservable(userId)
                .combine(postsRepository.getPostsByUserObservable(userId)) { user, posts ->
                    UserPostsWrapper(user, posts)
                }
                .collect { result ->
                    userPostsMutableLiveData.value = result
                }
        }
    }

    fun getUserPostsLiveData(): LiveData<UserPostsWrapper> {
        return userPostsMutableLiveData
    }

}
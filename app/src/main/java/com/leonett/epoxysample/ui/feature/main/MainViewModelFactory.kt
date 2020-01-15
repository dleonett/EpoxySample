package com.leonett.epoxysample.ui.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leonett.epoxysample.data.PostsRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val postsRepository: PostsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(postsRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }

}
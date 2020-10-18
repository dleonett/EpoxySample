package com.leonett.photofeed.ui.feature.inbox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leonett.photofeed.data.PostsRepository
import javax.inject.Inject

class InboxViewModelFactory @Inject constructor() :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(InboxViewModel::class.java)) {
            InboxViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }

}
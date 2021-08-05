package com.leonett.photofeed.ui.feature.hub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leonett.photofeed.data.ScreensRepository
import javax.inject.Inject

class HubViewModelFactory @Inject constructor(private val screensRepository: ScreensRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HubViewModel::class.java)) {
            HubViewModel(screensRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }

}
package com.leonett.photofeed.ui.feature.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leonett.photofeed.data.UserRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(userRepository: UserRepository) :
    ViewModel() {

    private var resultLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        resultLiveData.value = !userRepository.getLocalUser().isNullOrEmpty()
    }

    fun getResultLiveData(): LiveData<Boolean> = resultLiveData

}
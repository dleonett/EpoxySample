package com.leonett.photofeed.ui.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonett.photofeed.data.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val loginScreenStateLiveData: MutableLiveData<LoginScreenState> = MutableLiveData()

    fun login(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            loginScreenStateLiveData.value = LoginScreenState.Error("Fill all inputs")
        } else {
            attemptToLogin(username, password)
        }
    }

    private fun attemptToLogin(username: String, password: String) {
        loginScreenStateLiveData.value = LoginScreenState.Loading
        viewModelScope.launch {
            delay(1500) // TODO: 23/06/20 Call Instagram endpoint to check if user exists and only then navigate to Home

            userRepository.storeLocalUser(username)
            loginScreenStateLiveData.value = LoginScreenState.Success
        }
    }

    fun getLoginScreenStateLiveData(): LiveData<LoginScreenState> = loginScreenStateLiveData

}
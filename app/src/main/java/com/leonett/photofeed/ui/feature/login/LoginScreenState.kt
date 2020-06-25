package com.leonett.photofeed.ui.feature.login

sealed class LoginScreenState {
    object Loading : LoginScreenState()

    object Success : LoginScreenState()

    data class Error(val message: String) : LoginScreenState()
}
package com.leonett.epoxysample.ui.feature.main

import com.leonett.epoxysample.ui.viewobject.MainScreenData

sealed class MainScreenState {
    data class Loading(val mainScreenData: MainScreenData, val loadMore: Boolean) :
        MainScreenState()

    data class Success(val mainScreenData: MainScreenData, val loadMore: Boolean) :
        MainScreenState()

    data class Error(
        val mainScreenData: MainScreenData,
        val message: String?,
        val loadMore: Boolean
    ) :
        MainScreenState()
}
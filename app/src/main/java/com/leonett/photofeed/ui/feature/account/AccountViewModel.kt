package com.leonett.photofeed.ui.feature.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonett.photofeed.data.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private var _userInfoLiveData: MutableLiveData<UserInfo> = MutableLiveData()
    val userInfoLiveData: LiveData<UserInfo>
        get() = _userInfoLiveData

    private var _friendsInfoLiveData: MutableLiveData<FriendsInfo> = MutableLiveData()
    val friendsInfoLiveData: LiveData<FriendsInfo>
        get() = _friendsInfoLiveData

    private var _photosInfoLiveData: MutableLiveData<PhotosInfo> = MutableLiveData()
    val photosInfoLiveData: LiveData<PhotosInfo>
        get() = _photosInfoLiveData

    private var _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    private var _loaderLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loaderLiveData: LiveData<Boolean>
        get() = _loaderLiveData

    private var _screenStateLiveData: MutableLiveData<AccountScreenState> = MutableLiveData()
    val screenStateLiveData: LiveData<AccountScreenState>
        get() = _screenStateLiveData

    init {
        viewModelScope.launch {
            //successMultipleLiveData()
            //errorMultipleLiveData()

            successSingleViewState()
            //errorSingleViewState()
        }
    }

    private suspend fun successSingleViewState() {
        _screenStateLiveData.value = AccountScreenState.Loading

        delay(2000)

        _screenStateLiveData.value = AccountScreenState.Success(
            UserInfo(
                "John Smith",
                "New York",
                "https://cactusthemes.com/blog/wp-content/uploads/2018/01/tt_avatar_small.jpg"
            ),
            FriendsInfo(200),
            PhotosInfo(90)
        )
    }

    private suspend fun errorSingleViewState() {
        _screenStateLiveData.value = AccountScreenState.Loading

        delay(2000)

        _screenStateLiveData.value = AccountScreenState.Error("User does not exist")
    }

    private suspend fun successMultipleLiveData() {
        _loaderLiveData.value = true

        delay(2000)

        _loaderLiveData.value = false

        _userInfoLiveData.value =
            UserInfo(
                "John Smith",
                "New York",
                "https://cactusthemes.com/blog/wp-content/uploads/2018/01/tt_avatar_small.jpg"
            )

        _friendsInfoLiveData.value = FriendsInfo(200)

        _photosInfoLiveData.value = PhotosInfo(90)
    }

    private suspend fun errorMultipleLiveData() {
        _loaderLiveData.value = true

        delay(2000)

        _loaderLiveData.value = false

        _errorLiveData.value = "User does not exist"
    }

    fun logoutUser() {
        userRepository.clearLocalUser()
    }

}

data class UserInfo(val name: String, val city: String, val avatarUrl: String)
data class PhotosInfo(val count: Int)
data class FriendsInfo(val count: Int)

sealed class AccountScreenState {
    object Loading : AccountScreenState()
    data class Success(
        val userInfo: UserInfo,
        val friendsInfo: FriendsInfo,
        val photosInfo: PhotosInfo
    ) : AccountScreenState()

    data class Error(val message: String) : AccountScreenState()
}
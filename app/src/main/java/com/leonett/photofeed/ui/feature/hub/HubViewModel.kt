package com.leonett.photofeed.ui.feature.hub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonett.photofeed.data.ScreensRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class HubViewModel @Inject constructor(private val screensRepository: ScreensRepository) :
    ViewModel() {

    private val _state = MutableStateFlow<HubScreenState>(HubScreenState.Idle)
    val state: StateFlow<HubScreenState>
        get() = _state

    private val screenMaxId = 2
    private var screenId = 1

    init {
        fetchScreenFromRemote()
    }

    private fun fetchScreenFromRemote() {
        viewModelScope.launch {
            _state.value = HubScreenState.Loading(HubScreenData(""))

            try {
                val composableScreen = screensRepository.getScreen(calculateNextScreenId())
                _state.value = HubScreenState.Success(
                    HubScreenData(
                        screenTitle = composableScreen.title.orEmpty(),
                        sections = composableScreen.sections
                    )
                )
            } catch (e: Throwable) {
                _state.value = HubScreenState.Error(HubScreenData("", ""))
            }
        }
    }

    private fun calculateNextScreenId(): Int {
        screenId = if (screenId == screenMaxId) 1 else screenId + 1
        return screenId
    }

    fun refresh() {
        fetchScreenFromRemote()
    }

}

sealed class HubScreenState {
    object Idle : HubScreenState()
    data class Loading(val screenData: HubScreenData) : HubScreenState()
    data class Success(val screenData: HubScreenData) : HubScreenState()
    data class Error(val screenData: HubScreenData) : HubScreenState()
}
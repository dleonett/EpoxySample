package com.leonett.photofeed.ui.feature.inbox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonett.photofeed.data.model.Conversation
import com.leonett.photofeed.data.model.Message
import com.leonett.photofeed.data.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class InboxViewModel : ViewModel() {

    private val _state = MutableStateFlow<InboxScreenState>(InboxScreenState.Idle)
    val state: StateFlow<InboxScreenState>
        get() = _state

    fun initialize() {
        viewModelScope.launch {
            _state.value = InboxScreenState.Loading()

            delay(2000)

            val users = User.generateDummyList()
            val me = users.last()
            val conversations = mutableListOf(
                Conversation(
                    "1", users[0], Message("1", "Hello mate!", "2020-10-19 00:15:00", me, users[0])
                ),
                Conversation(
                    "2",
                    users[1],
                    Message("2", "Sure, let's meet there", "2020-10-15 19:00:00", me, users[1])
                ),
                Conversation(
                    "3", users[2], Message("3", "Awesomeee", "2020-08-14 17:34:00", me, users[2])
                ),
                Conversation(
                    "4",
                    users[3],
                    Message(
                        "4",
                        "Yes he told me we can get there by noon",
                        "2020-06-10 14:29:00",
                        me,
                        users[3]
                    )
                ),
                Conversation(
                    "5", users[4], Message("5", "See you!", "2020-05-10 18:00:00", me, users[4])
                )
            )

            _state.value = InboxScreenState.Success(conversations)
        }
    }

}

sealed class InboxScreenState {
    object Idle : InboxScreenState()
    data class Loading(val conversations: List<Conversation>? = null) : InboxScreenState()
    data class Success(val conversations: List<Conversation>) : InboxScreenState()
    data class Error(val message: String) : InboxScreenState()
}
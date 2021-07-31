package com.leonett.photofeed.ui.compose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leonett.photofeed.data.model.Conversation
import com.leonett.photofeed.ui.compose.constants.Dimens
import com.leonett.photofeed.ui.compose.widget.ContainerWithHeader
import com.leonett.photofeed.ui.compose.widget.Conversation
import com.leonett.photofeed.ui.feature.inbox.InboxScreenState
import com.leonett.photofeed.ui.feature.inbox.InboxViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun InboxScreen(
    viewModel: InboxViewModel = InboxViewModel(),
    onConversationClick: ((conversation: Conversation) -> Unit)? = null
) {
    val state by remember(viewModel) { viewModel.state }.collectAsState()

    ContainerWithHeader("Inbox") {
        when (state) {
            is InboxScreenState.Idle -> {
                // Do nothing
            }
            is InboxScreenState.Loading -> {
                LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
                    items(
                        (state as InboxScreenState.Loading).conversations ?: listOf()
                    ) { conversation ->
                        Conversation(conversation) { onConversationClick?.invoke(conversation) }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(Dimens.LOADER_BASIC_SIZE)
                            .align(Alignment.Center)
                    )
                }
            }
            is InboxScreenState.Success -> {
                LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
                    items((state as InboxScreenState.Success).conversations) { conversation ->
                        Conversation(conversation) { onConversationClick?.invoke(conversation) }
                    }
                }
            }
            is InboxScreenState.Error -> {
                Text("Error")
            }
        }
    }
}

@ExperimentalCoroutinesApi
@Preview(showBackground = true)
@Composable
fun PreviewInboxScreen() {
    InboxScreen()
}
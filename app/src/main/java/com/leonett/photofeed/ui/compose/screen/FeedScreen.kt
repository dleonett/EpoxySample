package com.leonett.photofeed.ui.compose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leonett.photofeed.ui.compose.constants.Dimens
import com.leonett.photofeed.ui.compose.widget.ContainerWithHeader
import com.leonett.photofeed.ui.compose.widget.Post
import com.leonett.photofeed.ui.feature.feed.FeedScreenState

@Composable
fun FeedScreen(state: FeedScreenState = FeedScreenState.Idle) {
    ContainerWithHeader("Feed") {
        when (state) {
            is FeedScreenState.Idle -> {
                // Do nothing
            }
            is FeedScreenState.Loading -> {
                LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
                    items(state.feedScreenData.postsStoriesWrapper.posts) { post ->
                        Post(post)
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
            is FeedScreenState.Success -> {
                LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
                    items(state.feedScreenData.postsStoriesWrapper.posts) { post ->
                        Post(post)
                    }
                }
            }
            is FeedScreenState.Error -> {
                Text("Error")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFeedScreen() {
    FeedScreen()
}
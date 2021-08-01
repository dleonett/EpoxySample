package com.leonett.photofeed.ui.compose.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leonett.photofeed.R
import com.leonett.photofeed.data.model.Post
import com.leonett.photofeed.data.model.PostsStoriesWrapper
import com.leonett.photofeed.data.model.Story
import com.leonett.photofeed.ui.compose.constants.Colors
import com.leonett.photofeed.ui.compose.constants.Dimens
import com.leonett.photofeed.ui.compose.widget.Header
import com.leonett.photofeed.ui.compose.widget.Post
import com.leonett.photofeed.ui.compose.widget.Stories
import com.leonett.photofeed.ui.feature.feed.FeedScreenState
import com.leonett.photofeed.ui.feature.feed.FeedViewModel
import com.leonett.photofeed.ui.viewobject.FeedScreenData

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun FeedScreen(
    viewModel: FeedViewModel,
    onStoryClick: ((story: Story) -> Unit)? = null,
    onPostAvatarClick: ((post: Post) -> Unit)? = null,
    onPostMoreClick: ((post: Post) -> Unit)? = null,
    onPostLikeClick: ((post: Post) -> Unit)? = null,
    onPostCommentClick: ((post: Post) -> Unit)? = null,
    onPostShareClick: ((post: Post) -> Unit)? = null,
    onPostContentDoubleClick: ((post: Post) -> Unit)? = null
) {
    val state by remember(viewModel) { viewModel.state }.collectAsState()
    when (state) {
        is FeedScreenState.Idle -> {
            // Do nothing
        }
        is FeedScreenState.Loading -> {
            val screenState = (state as FeedScreenState.Loading)
            FeedContent(
                data = screenState.feedScreenData,
                onStoryClick = onStoryClick,
                onPostAvatarClick = onPostAvatarClick,
                onPostMoreClick = onPostMoreClick,
                onPostLikeClick = onPostLikeClick,
                onPostCommentClick = onPostCommentClick,
                onPostShareClick = onPostShareClick,
                onPostContentDoubleClick = onPostContentDoubleClick
            )
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
            val screenState = state as FeedScreenState.Success
            FeedContent(
                data = screenState.feedScreenData,
                onStoryClick = onStoryClick,
                onPostAvatarClick = onPostAvatarClick,
                onPostMoreClick = onPostMoreClick,
                onPostLikeClick = onPostLikeClick,
                onPostCommentClick = onPostCommentClick,
                onPostShareClick = onPostShareClick,
                onPostContentDoubleClick = onPostContentDoubleClick
            )
        }
        is FeedScreenState.Error -> {
            Text("Error")
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun FeedContent(
    data: FeedScreenData,
    onStoryClick: ((story: Story) -> Unit)? = null,
    onPostAvatarClick: ((post: Post) -> Unit)? = null,
    onPostMoreClick: ((post: Post) -> Unit)? = null,
    onPostLikeClick: ((post: Post) -> Unit)? = null,
    onPostCommentClick: ((post: Post) -> Unit)? = null,
    onPostShareClick: ((post: Post) -> Unit)? = null,
    onPostContentDoubleClick: ((post: Post) -> Unit)? = null
) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState, contentPadding = PaddingValues(bottom = 8.dp)) {
        stickyHeader {
            Header(title = stringResource(id = R.string.feed_screen_title))

            val showDivider by remember {
                derivedStateOf { listState.firstVisibleItemScrollOffset != 0 }
            }
            AnimatedVisibility(visible = showDivider) {
                Divider(color = Colors.DIVIDER_COLOR)
            }
        }
        item {
            Stories(
                stories = data.postsStoriesWrapper.stories,
                onStoryClick = onStoryClick
            )
            Divider(color = Colors.DIVIDER_COLOR)
        }
        items(data.postsStoriesWrapper.posts) { post ->
            Post(
                post = post,
                onPostAvatarClick = onPostAvatarClick,
                onPostMoreClick = onPostMoreClick,
                onPostLikeClick = onPostLikeClick,
                onPostCommentClick = onPostCommentClick,
                onPostShareClick = onPostShareClick,
                onPostContentDoubleClick = onPostContentDoubleClick
            )
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun PreviewFeedContent() {
    FeedContent(
        data = FeedScreenData(
            0,
            PostsStoriesWrapper(Post.generateDummyList(), Story.generateDummyList())
        )
    )
}

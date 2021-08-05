package com.leonett.photofeed.ui.compose.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leonett.photofeed.data.mapper.Action
import com.leonett.photofeed.data.mapper.RecentContactsSection
import com.leonett.photofeed.ui.compose.constants.Dimens
import com.leonett.photofeed.ui.compose.widget.RecentContacts
import com.leonett.photofeed.ui.feature.hub.ComposableScreenData
import com.leonett.photofeed.ui.feature.hub.HubScreenState
import com.leonett.photofeed.ui.feature.hub.HubViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun HubScreen(
    viewModel: HubViewModel,
    onNavigateBackClick: (() -> Unit)? = null,
    onRefresh: (() -> Unit)? = null,
    onActionClick: ((action: Action) -> Unit)? = null
) {
    val state by remember(viewModel) { viewModel.state }.collectAsState()
    when (state) {
        is HubScreenState.Idle -> {
        }
        is HubScreenState.Loading -> {
            val screenData = (state as HubScreenState.Loading).screenData
            HubScreenLoading(
                screenData = screenData,
                onNavigateBackClick = onNavigateBackClick
            )
        }
        is HubScreenState.Success -> {
            val screenData = (state as HubScreenState.Success).screenData
            HubScreenContent(
                screenData = screenData,
                onRefresh = onRefresh,
                onNavigateBackClick = onNavigateBackClick,
                onActionClick = onActionClick
            )
        }
        is HubScreenState.Error -> {
            val screenData = (state as HubScreenState.Error).screenData
            HubScreenError(
                screenData = screenData,
                onRefresh = onRefresh,
                onNavigateBackClick = onNavigateBackClick
            )
        }
    }
}

@Composable
fun HubScreenLoading(
    screenData: ComposableScreenData,
    onNavigateBackClick: (() -> Unit)? = null
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(screenData.screenTitle) },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Navigate back button",
                    modifier = Modifier.clickable {
                        onNavigateBackClick?.invoke()
                    }
                )
            }
        )
    }) {
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
}

@Composable
fun HubScreenContent(
    screenData: ComposableScreenData,
    onRefresh: (() -> Unit)? = null,
    onNavigateBackClick: (() -> Unit)? = null,
    onActionClick: ((action: Action) -> Unit)? = null
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(screenData.screenTitle) },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Navigate back button",
                    modifier = Modifier.clickable {
                        onNavigateBackClick?.invoke()
                    }
                )
            },
            actions = {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh button",
                    modifier = Modifier.clickable {
                        onRefresh?.invoke()
                    }
                )
            })
    },
        floatingActionButton = {
            screenData.floatingAction?.let { floatingAction ->
                ExtendedFloatingActionButton(
                    text = { Text(floatingAction.text) },
                    icon = {
                        floatingAction.iconId?.let { iconId ->
                            var iconSource: ImageVector? = null
                            when (iconId) {
                                "add" -> iconSource = Icons.Default.Add
                                "done" -> iconSource = Icons.Default.Done
                                "close" -> iconSource = Icons.Default.Close
                            }

                            iconSource?.let {
                                Icon(
                                    imageVector = it,
                                    contentDescription = "Floating action button"
                                )
                            }
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    onClick = {
                        floatingAction.action?.let {
                            onActionClick?.invoke(it)
                        }
                    }
                )
            }
        }) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(screenData.sections ?: listOf()) { section ->
                when (section) {
                    is RecentContactsSection -> RecentContacts(
                        contacts = section.contacts,
                        recentContactsTitle = section.title,
                        viewAllContactsTitle = section.viewAllTitle,
                        onActionClick = onActionClick
                    )
                }
            }
        }
    }
}

@Composable
fun HubScreenError(
    screenData: ComposableScreenData,
    onRefresh: (() -> Unit)? = null,
    onNavigateBackClick: (() -> Unit)? = null
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("") },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Navigate back button",
                    modifier = Modifier.clickable {
                        onNavigateBackClick?.invoke()
                    }
                )
            },
            actions = {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh button",
                    modifier = Modifier.clickable {
                        onRefresh?.invoke()
                    }
                )
            })
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = screenData.errorMessage.orEmpty(),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun PreviewHubScreenLoading() {
    HubScreenLoading(ComposableScreenData(""))
}

@Preview
@Composable
fun PreviewHubScreenContent() {
    HubScreenContent(ComposableScreenData("Amigos"))
}

@Preview
@Composable
fun PreviewHubScreenError() {
    HubScreenError(
        ComposableScreenData(
            screenTitle = "Amigos",
            errorMessage = "Something went wrong"
        )
    )
}

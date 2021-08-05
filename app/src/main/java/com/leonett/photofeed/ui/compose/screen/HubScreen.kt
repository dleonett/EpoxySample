package com.leonett.photofeed.ui.compose.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leonett.photofeed.data.mapper.Action
import com.leonett.photofeed.data.mapper.ActivitiesSection
import com.leonett.photofeed.data.mapper.RecentContactsSection
import com.leonett.photofeed.data.mapper.TopBar
import com.leonett.photofeed.ui.compose.constants.Dimens
import com.leonett.photofeed.ui.compose.widget.ActionIcon
import com.leonett.photofeed.ui.compose.widget.Activities
import com.leonett.photofeed.ui.compose.widget.FloatingActionIcon
import com.leonett.photofeed.ui.compose.widget.RecentContacts
import com.leonett.photofeed.ui.feature.hub.ComposableScreenData
import com.leonett.photofeed.ui.feature.hub.HubScreenState
import com.leonett.photofeed.ui.feature.hub.HubViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun HubScreen(
    viewModel: HubViewModel,
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
                onActionClick = onActionClick
            )
        }
        is HubScreenState.Success -> {
            val screenData = (state as HubScreenState.Success).screenData
            HubScreenContent(
                screenData = screenData,
                onActionClick = onActionClick
            )
        }
        is HubScreenState.Error -> {
            val screenData = (state as HubScreenState.Error).screenData
            HubScreenError(
                screenData = screenData,
                onActionClick = onActionClick
            )
        }
    }
}

@Composable
fun HubScreenLoading(
    screenData: ComposableScreenData,
    onActionClick: ((action: Action) -> Unit)? = null
) {
    Scaffold(topBar = {
        screenData.topBar?.let { topBar ->
            TopAppBar(
                title = { Text(topBar.title.orEmpty()) },
                navigationIcon = topBar.navIcon?.let {
                    { ActionIcon(icon = it, onActionClick = onActionClick) }
                },
                actions = {
                    topBar.actions?.forEach { icon ->
                        ActionIcon(icon = icon, onActionClick = onActionClick)
                    }
                }
            )
        }
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
    onActionClick: ((action: Action) -> Unit)? = null
) {
    Scaffold(topBar = {
        screenData.topBar?.let { topBar ->
            TopAppBar(
                title = { Text(topBar.title.orEmpty()) },
                navigationIcon = topBar.navIcon?.let {
                    { ActionIcon(icon = it, onActionClick = onActionClick) }
                },
                actions = {
                    topBar.actions?.forEach { icon ->
                        ActionIcon(icon = icon, onActionClick = onActionClick)
                    }
                }
            )
        }
    },
        floatingActionButton = {
            screenData.floatingAction?.let { floatingAction ->
                ExtendedFloatingActionButton(
                    text = { Text(floatingAction.text) },
                    icon = floatingAction.iconId?.let { iconId ->
                        { FloatingActionIcon(iconId) }
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
                    is ActivitiesSection -> Activities(section.items, onActionClick)
                }
            }
        }
    }
}

@Composable
fun HubScreenError(
    screenData: ComposableScreenData,
    onActionClick: ((action: Action) -> Unit)? = null
) {
    Scaffold(topBar = {
        screenData.topBar?.let { topBar ->
            TopAppBar(
                title = { Text(topBar.title.orEmpty()) },
                navigationIcon = topBar.navIcon?.let {
                    { ActionIcon(icon = it, onActionClick = onActionClick) }
                },
                actions = {
                    topBar.actions?.forEach { icon ->
                        ActionIcon(icon = icon, onActionClick = onActionClick)
                    }
                }
            )
        }
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
    HubScreenLoading(ComposableScreenData(TopBar("Amigos")))
}

@Preview
@Composable
fun PreviewHubScreenContent() {
    HubScreenContent(ComposableScreenData(TopBar("Amigos")))
}

@Preview
@Composable
fun PreviewHubScreenError() {
    HubScreenError(
        ComposableScreenData(
            topBar = TopBar("Amigos"),
            errorMessage = "Something went wrong"
        )
    )
}

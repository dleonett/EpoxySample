package com.leonett.photofeed.ui.compose.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leonett.photofeed.data.mapper.*
import com.leonett.photofeed.ui.compose.constants.Dimens
import com.leonett.photofeed.ui.compose.extensions.getContainerByType
import com.leonett.photofeed.ui.compose.extensions.getSectionByType
import com.leonett.photofeed.ui.compose.widget.ActionIcon
import com.leonett.photofeed.ui.compose.widget.Activities
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
            HubScreenLoading()
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
            HubScreenError(screenData = screenData)
        }
    }
}

@Composable
fun HubScreenLoading() {
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

@Composable
fun HubScreenContent(
    screenData: ComposableScreenData,
    onActionClick: ((action: Action) -> Unit)? = null
) {
    val containers = screenData.containers
    Scaffold(
        topBar = {
            getContainerByType<TopBarContainer>(containers)?.let { topBar ->
                TopAppBar(
                    title = { Text(topBar.title.orEmpty()) },
                    navigationIcon = getSectionByType<NavIconSection>(topBar.sections)?.let {
                        {
                            ActionIcon(
                                icon = Icon("back"),
                                action = it.action,
                                onActionClick = onActionClick
                            )
                        }
                    },
                    actions = {
                        val topBarActions = topBar.sections?.filterNot { it is NavIconSection }
                        topBarActions?.forEach { section ->
                            val icon = when (section) {
                                is ProfileIconSection -> Icon("user")
                                is ShareIconSection -> Icon("share")
                                is RefreshIconSection -> Icon("refresh")
                                else -> null
                            }

                            section?.let {
                                ActionIcon(
                                    icon = icon,
                                    action = it.action,
                                    onActionClick = onActionClick
                                )
                            }
                        }
                    }
                )
            }
        },
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            getContainerByType<MainContentContainer>(containers)?.let { content ->
                items(content.sections ?: listOf()) { section ->
                    when (section) {
                        is RecentContactsSection -> {
                            if (section.sections?.isEmpty() == false) {
                                RecentContacts(
                                    contacts = Contact.generateDummyList(),
                                    recentContactsTitle = Title(
                                        text = section.title ?: "Recientes"
                                    ),
                                    viewAllContactsTitle = section.viewAllTitle
                                        ?: Title(text = "Ver agenda"),
                                    onActionClick = onActionClick
                                )
                            }
                        }
                        is ActivitiesSection -> {
                            if (section.sections?.isEmpty() == false) {
                                Activities(
                                    activities = ActivityCard.generateDummyList(),
                                    onActionClick = onActionClick)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HubScreenError(
    screenData: ComposableScreenData
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("") },
            actions = {}
        )
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
    HubScreenLoading()
}

@Preview
@Composable
fun PreviewHubScreenContent() {
    HubScreenContent(ComposableScreenData(listOf()))
}

@Preview
@Composable
fun PreviewHubScreenError() {
    HubScreenError(
        ComposableScreenData(
            errorMessage = "Something went wrong"
        )
    )
}

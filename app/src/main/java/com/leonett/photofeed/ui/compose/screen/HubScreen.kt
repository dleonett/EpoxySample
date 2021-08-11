package com.leonett.photofeed.ui.compose.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.leonett.photofeed.data.mapper.Action
import com.leonett.photofeed.data.mapper.Icon
import com.leonett.photofeed.data.mapper.NavIconSection
import com.leonett.photofeed.data.mapper.TopBarContainer
import com.leonett.photofeed.ui.compose.constants.Dimens
import com.leonett.photofeed.ui.compose.widget.ActionIcon
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
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Aguanta") },
            actions = {}
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
    onActionClick: ((action: Action) -> Unit)? = null
) {
    val containers = screenData.containers
    Scaffold(
        topBar = {
            (containers?.firstOrNull { it is TopBarContainer } as? TopBarContainer)?.let { topBar ->
                TopAppBar(
                    title = { Text(topBar.title.orEmpty()) },
                    navigationIcon = (topBar.sections?.firstOrNull { it is NavIconSection } as? NavIconSection)?.let {
                        { ActionIcon(icon = Icon("back"), action = it.action, onActionClick = onActionClick) }
                    }
                )
            }
        },
    ) {
//        LazyColumn(modifier = Modifier.fillMaxSize()) {
//            items(screenData.sections ?: listOf()) { section ->
//                when (section) {
//                    is RecentContactsSection -> RecentContacts(
//                        contacts = section.contacts,
//                        recentContactsTitle = section.title,
//                        viewAllContactsTitle = section.viewAllTitle,
//                        onActionClick = onActionClick
//                    )
//                    is ActivitiesSection -> Activities(section.items, onActionClick)
//                }
//            }
//        }
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
//    HubScreenLoading(ComposableScreenData(TopBar("Amigos")))
}

@Preview
@Composable
fun PreviewHubScreenContent() {
//    HubScreenContent(ComposableScreenData(TopBar("Amigos")))
}

@Preview
@Composable
fun PreviewHubScreenError() {
    HubScreenError(
        ComposableScreenData(
//            topBar = TopBar("Amigos"),
            errorMessage = "Something went wrong"
        )
    )
}

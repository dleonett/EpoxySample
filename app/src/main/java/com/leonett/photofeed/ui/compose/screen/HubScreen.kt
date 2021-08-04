package com.leonett.photofeed.ui.compose.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.leonett.photofeed.data.mapper.Action
import com.leonett.photofeed.data.mapper.RecentContactsSection
import com.leonett.photofeed.data.mapper.Title
import com.leonett.photofeed.data.model.Contact
import com.leonett.photofeed.ui.compose.widget.RecentContacts
import com.leonett.photofeed.ui.feature.hub.HubScreenData
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun HubScreen(
    screenData: HubScreenData,
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
            })
    }) {
        LazyColumn {
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

@ExperimentalCoroutinesApi
@Preview
@Composable
fun PreviewHubScreen() {
    HubScreen(
        HubScreenData(
            "Amigos",
            listOf(
                RecentContactsSection(
                    Title("Recientes"),
                    Title("Ver Agenda"),
                    Contact.generateDummyList()
                )
            )
        )
    )
}

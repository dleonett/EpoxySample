package com.leonett.photofeed.ui.feature.hub

import android.view.View
import androidx.compose.ui.platform.ComposeView
import com.leonett.photofeed.R
import com.leonett.photofeed.data.mapper.Action
import com.leonett.photofeed.data.mapper.RecentContactsSection
import com.leonett.photofeed.data.mapper.Section
import com.leonett.photofeed.data.mapper.Title
import com.leonett.photofeed.data.model.Contact
import com.leonett.photofeed.ui.base.BaseFragment
import com.leonett.photofeed.ui.compose.screen.HubScreen
import com.leonett.photofeed.ui.compose.theme.BlueTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HubFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_compose

    override fun initVars() {
        // no-op
    }

    override fun initViews(view: View) {
        val sections = listOf<Section>(
            RecentContactsSection(
                Title("Recientes"),
                Title("Ver Agenda", Action("deeplink", "mp://this/is/an/uri")),
                Contact.generateDummyList()
            )
        )

        (view as ComposeView).setContent {
            BlueTheme {
                HubScreen(
                    screenData = HubScreenData("Amigos", sections),
                    onNavigateBackClick = { },
                    onActionClick = { action -> showToast("URI: ${action.uri}") }
                )
            }
        }
    }

}

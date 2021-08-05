package com.leonett.photofeed.ui.feature.hub

import android.content.Context
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import com.leonett.photofeed.App
import com.leonett.photofeed.R
import com.leonett.photofeed.ui.base.BaseFragment
import com.leonett.photofeed.ui.compose.screen.HubScreen
import com.leonett.photofeed.ui.compose.theme.BlueTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class HubFragment : BaseFragment() {

    @Inject
    lateinit var hubViewModelFactory: HubViewModelFactory

    lateinit var hubViewModel: HubViewModel

    override val layoutId: Int
        get() = R.layout.fragment_compose

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireContext().applicationContext as App).appComponent.hubComponent().create()
            .inject(this)
    }

    override fun initVars() {
        hubViewModel = ViewModelProvider(this, hubViewModelFactory)
            .get(HubViewModel::class.java)
    }

    override fun initViews(view: View) {
        (view as ComposeView).setContent {
            BlueTheme {
                HubScreen(
                    viewModel = hubViewModel,
                    onRefresh = hubViewModel::refresh,
                    onNavigateBackClick = { },
                    onActionClick = { action -> showToast("URI: ${action.uri}") }
                )
            }
        }
    }

}

package com.leonett.photofeed.ui.feature.inbox

import android.content.Context
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leonett.photofeed.App
import com.leonett.photofeed.R
import com.leonett.photofeed.data.model.Conversation
import com.leonett.photofeed.ui.base.BaseFragment
import com.leonett.photofeed.ui.compose.screen.InboxScreen
import com.leonett.photofeed.ui.feature.inbox.conversation.ChatFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class InboxFragment : BaseFragment() {

    @Inject
    lateinit var inboxViewModelFactory: InboxViewModelFactory

    lateinit var inboxViewModel: InboxViewModel

    override val layoutId: Int
        get() = R.layout.fragment_compose

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireContext().applicationContext as App).appComponent.inboxComponent().create()
            .inject(this)
    }

    override fun initVars() {
        inboxViewModel = ViewModelProvider(this, inboxViewModelFactory)
            .get(InboxViewModel::class.java)

        inboxViewModel.initialize()
    }

    override fun initViews(view: View) {
        (view as ComposeView).setContent {
            InboxScreen(inboxViewModel) {
                navigateToChat(it)
            }
        }
    }

    private fun navigateToChat(conversation: Conversation) {
        findNavController().navigate(
            R.id.actionChat,
            ChatFragment.createArguments(
                conversation.id,
                conversation.user.displayName,
                conversation.user.username
            )
        )
    }

}

package com.leonett.photofeed.ui.feature.inbox

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonett.photofeed.App
import com.leonett.photofeed.R
import com.leonett.photofeed.data.model.Conversation
import com.leonett.photofeed.ui.base.BaseFragment
import com.leonett.photofeed.ui.feature.inbox.conversation.ChatFragment
import kotlinx.android.synthetic.main.fragment_inbox.*
import javax.inject.Inject

class InboxFragment : BaseFragment(), InboxController.OnInteractionListener {

    @Inject
    lateinit var inboxViewModelFactory: InboxViewModelFactory

    lateinit var inboxViewModel: InboxViewModel

    private val inboxController = InboxController(this)

    override val layoutId: Int
        get() = R.layout.fragment_inbox

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireContext().applicationContext as App).appComponent.inboxComponent().create()
            .inject(this)
    }

    override fun initVars() {
        inboxViewModel = ViewModelProvider(this, inboxViewModelFactory)
            .get(InboxViewModel::class.java)
    }

    override fun initViews(view: View) {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            setController(inboxController)

            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
            dividerItemDecoration.setDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.item_divider
                )!!
            )
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun observeViewModels() {
        inboxViewModel.getScreenStateLiveData().observe(viewLifecycleOwner, Observer {
            handleScreenState(it)
        })
    }

    private fun handleScreenState(state: InboxScreenState) {
        when (state) {
            is InboxScreenState.Loading -> {
                inboxController.setData(state.conversations, true)
                progressLoader.visibility = View.VISIBLE
            }
            is InboxScreenState.Success -> {
                inboxController.setData(state.conversations, false)
                progressLoader.visibility = View.GONE
            }
            else -> {
                inboxController.setData(null, false)
                progressLoader.visibility = View.GONE
            }
        }
    }

    override fun onConversationClick(conversation: Conversation) {
        navigateToChat(conversation)
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

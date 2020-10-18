package com.leonett.photofeed.ui.feature.inbox.conversation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.leonett.photofeed.R
import com.leonett.photofeed.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : BaseFragment() {

    private var conversationId: String? = null
    private var userDisplayName: String? = null
    private var username: String? = null

    override val layoutId: Int
        get() = R.layout.fragment_chat

    override fun initVars() {
        arguments?.let {
            conversationId = it.getString(ARG_CONVERSATION_ID)
            userDisplayName = it.getString(ARG_USER_DISPLAY_NAME)
            username = it.getString(ARG_USERNAME)
        }
    }

    override fun initViews(view: View) {
        setupTopBar()
    }

    private fun setupTopBar() {
        topBarTitle.text = userDisplayName
        topBarSubtitle.text = username

        btnNavigationUp.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    companion object {

        private const val ARG_CONVERSATION_ID = "conversationId"
        private const val ARG_USER_DISPLAY_NAME = "userDisplayName"
        private const val ARG_USERNAME = "username"

        fun createArguments(
            conversationId: String,
            userDisplayName: String,
            username: String
        ): Bundle {
            val bundle = Bundle()
            bundle.putString(ARG_CONVERSATION_ID, conversationId)
            bundle.putString(ARG_USER_DISPLAY_NAME, userDisplayName)
            bundle.putString(ARG_USERNAME, username)

            return bundle
        }
    }

}

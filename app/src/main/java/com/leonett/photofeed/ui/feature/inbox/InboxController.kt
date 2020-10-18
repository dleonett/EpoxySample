package com.leonett.photofeed.ui.feature.inbox

import com.airbnb.epoxy.Typed2EpoxyController
import com.leonett.photofeed.data.model.Conversation
import com.leonett.photofeed.ui.adapter.conversation

class InboxController(private var onInteractionListener: OnInteractionListener? = null) :
    Typed2EpoxyController<List<Conversation>, Boolean>() {

    override fun buildModels(conversations: List<Conversation>?, isLoading: Boolean) {
        conversations?.let {
            it.forEach { conversation ->
                conversation {
                    id(CONVERSATION_ID + conversation.id)
                    conversation(conversation)
                    itemClickListener { _ ->
                        onInteractionListener?.onConversationClick(conversation)
                    }
                }
            }
        }
    }

    interface OnInteractionListener {
        fun onConversationClick(conversation: Conversation)
    }

    companion object {
        private const val CONVERSATION_ID = "CONVERSATION_ID"
        private const val LOADER_ID = "LOADER_ID"
    }
}

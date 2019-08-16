package com.leonett.epoxysample.ui.feature.main

import android.view.View
import com.airbnb.epoxy.Typed3EpoxyController
import com.leonett.epoxysample.data.Post
import com.leonett.epoxysample.data.Story
import com.leonett.epoxysample.ui.adapter.footer
import com.leonett.epoxysample.ui.adapter.header
import com.leonett.epoxysample.ui.adapter.post
import com.leonett.epoxysample.ui.adapter.stories

class MainController : Typed3EpoxyController<String, List<Story>, List<Post>>() {

    private var onInteractionListener: OnInteractionListener? = null

    override fun buildModels(title: String, stories: List<Story>, posts: List<Post>) {
        header {
            id(HEADER_ID)
            title(title)
        }

        stories {
            id(STORIES_ID)
            stories(stories)
        }

        posts.forEach {
            post {
                id(it.id)
                post(it)
                itemClickListener { _: View? ->
                    onInteractionListener?.onItemClickListener(it)
                }
            }
        }

        footer {
            id(FOOTER_ID)
        }
    }

    fun setOnItemClickListener(onInteractionListener: OnInteractionListener) {
        this.onInteractionListener = onInteractionListener
    }

    interface OnInteractionListener {
        fun onItemClickListener(item: Post)
    }

    companion object {
        private const val HEADER_ID = "HEADER_ID"
        private const val STORIES_ID = "STORIES_ID"
        private const val FOOTER_ID = "FOOTER_ID"
    }
}
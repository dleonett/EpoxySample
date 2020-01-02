package com.leonett.epoxysample.ui.feature.main

import android.view.View
import com.airbnb.epoxy.Typed4EpoxyController
import com.leonett.epoxysample.data.Post
import com.leonett.epoxysample.data.Story
import com.leonett.epoxysample.ui.adapter.*

class MainController : Typed4EpoxyController<String, List<Story>, List<Post>, Boolean>(),
    StoriesModel.OnInteractionListener {

    private var onInteractionListener: OnInteractionListener? = null

    override fun buildModels(
        title: String,
        stories: List<Story>,
        posts: List<Post>,
        loadMore: Boolean
    ) {
        header {
            id(HEADER_ID)
            title(title)
        }

        stories {
            id(STORIES_ID)
            stories(stories)
            onInteractionListener(this@MainController)
        }

        posts.forEach {
            post {
                id(it.id)
                post(it)
                itemClickListener { _: View? ->
                    onInteractionListener?.onPostClick(it)
                }
            }
        }

        if (loadMore) {
            loadMore {
                id(LOAD_MORE_ID)
                itemClickListener { _: View? ->
                    onInteractionListener?.onLoadMoreClick()
                }
            }
        } else {
            footer {
                id(FOOTER_ID)
            }
        }
    }

    fun setOnItemClickListener(onInteractionListener: OnInteractionListener) {
        this.onInteractionListener = onInteractionListener
    }

    override fun onStoryClick(story: Story) {
        onInteractionListener?.onStoryClick(story)
    }

    interface OnInteractionListener {
        fun onStoryClick(story: Story)
        fun onPostClick(post: Post)
        fun onLoadMoreClick()
    }

    companion object {
        private const val HEADER_ID = "HEADER_ID"
        private const val STORIES_ID = "STORIES_ID"
        private const val FOOTER_ID = "FOOTER_ID"
        private const val LOAD_MORE_ID = "LOAD_MORE_ID"
    }
}
package com.leonett.epoxysample.ui.feature.main

import android.view.View
import com.airbnb.epoxy.Typed3EpoxyController
import com.leonett.epoxysample.data.model.HomeData
import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.data.model.Story
import com.leonett.epoxysample.ui.adapter.*

class MainController : Typed3EpoxyController<HomeData, Boolean, Boolean>(),
    StoriesModel.OnInteractionListener {

    private var onInteractionListener: OnInteractionListener? = null

    override fun buildModels(homeData: HomeData, loadMore: Boolean, isLoading: Boolean) {
        header {
            id(HEADER_ID)
            title("My feed")
        }

        stories {
            id(STORIES_ID)
            stories(homeData.stories)
            onInteractionListener(this@MainController)
        }

        homeData.posts.forEach {
            post {
                id(it.id)
                post(it)
                itemClickListener { _: View? ->
                    onInteractionListener?.onPostClick(it)
                }
            }
        }

        if (!isLoading) {
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
        } else {
            loader {
                id(LOADER_ID)
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
        private const val LOADER_ID = "LOADER_ID"
    }
}
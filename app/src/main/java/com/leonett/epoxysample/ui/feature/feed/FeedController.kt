package com.leonett.epoxysample.ui.feature.feed

import android.view.View
import com.airbnb.epoxy.Typed3EpoxyController
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.data.model.Story
import com.leonett.epoxysample.ui.adapter.*
import com.leonett.epoxysample.ui.viewobject.FeedScreenData

class FeedController : Typed3EpoxyController<FeedScreenData, Boolean, Boolean>(),
    StoriesModel.OnInteractionListener {

    private var onInteractionListener: OnInteractionListener? = null

    override fun buildModels(
        feedScreenData: FeedScreenData,
        loadMore: Boolean,
        isLoading: Boolean
    ) {
        header {
            id(HEADER_ID)
            title("My feed")
        }

        stories {
            id(STORIES_ID)
            stories(feedScreenData.postsStoriesWrapper.stories)
            onInteractionListener(this@FeedController)
        }

        feedScreenData.postsStoriesWrapper.posts.forEach {
            postHeader {
                id(POST_HEADER_ID + it.id)
                post(it)
                itemClickListener { _: View? ->
                    onInteractionListener?.onPostAvatarClick(it)
                }
            }

            post {
                id(it.id)
                post(it)
            }
        }

        if (isLoading) {
            loader {
                id(LOADER_ID)
            }
        } else {
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
                    titleResId(R.string.footer_text)
                }
            }
        }
    }

    override fun isStickyHeader(position: Int) =
        adapter.getModelAtPosition(position) is PostHeaderModel

    fun setOnItemClickListener(onInteractionListener: OnInteractionListener) {
        this.onInteractionListener = onInteractionListener
    }

    override fun onStoryClick(story: Story) {
        onInteractionListener?.onStoryClick(story)
    }

    interface OnInteractionListener {
        fun onStoryClick(story: Story)
        fun onPostAvatarClick(post: Post)
        fun onLoadMoreClick()
    }

    companion object {
        private const val HEADER_ID = "HEADER_ID"
        private const val POST_HEADER_ID = "POST_HEADER_ID"
        private const val STORIES_ID = "STORIES_ID"
        private const val FOOTER_ID = "FOOTER_ID"
        private const val LOAD_MORE_ID = "LOAD_MORE_ID"
        private const val LOADER_ID = "LOADER_ID"
    }
}
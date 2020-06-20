package com.leonett.photofeed.ui.feature.feed

import android.view.View
import com.airbnb.epoxy.Typed3EpoxyController
import com.leonett.photofeed.R
import com.leonett.photofeed.data.model.Post
import com.leonett.photofeed.data.model.Story
import com.leonett.photofeed.ui.adapter.*
import com.leonett.photofeed.ui.viewobject.FeedScreenData

class FeedController : Typed3EpoxyController<FeedScreenData, Boolean, Boolean>(),
    StoriesModel.OnInteractionListener,
    PostModel.OnInteractionListener {

    private var onInteractionListener: OnInteractionListener? = null

    override fun buildModels(
        feedScreenData: FeedScreenData,
        loadMore: Boolean,
        isLoading: Boolean
    ) {
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
                onInteractionListener(this@FeedController)
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

    fun setOnItemClickListener(onInteractionListener: OnInteractionListener) {
        this.onInteractionListener = onInteractionListener
    }

    override fun onStoryClick(story: Story) {
        onInteractionListener?.onStoryClick(story)
    }

    override fun onPostLikeClick(post: Post) {
        onInteractionListener?.onPostLikeClick(post)
    }

    override fun onPostLikeDoubleClick(post: Post) {
        onInteractionListener?.onPostLikeDoubleClick(post)
    }

    override fun onPostCommentClick(post: Post) {
        onInteractionListener?.onPostCommentClick(post)
    }

    override fun onPostShareClick(post: Post) {
        onInteractionListener?.onPostShareClick(post)
    }

    interface OnInteractionListener {
        fun onStoryClick(story: Story)
        fun onPostAvatarClick(post: Post)
        fun onPostLikeClick(post: Post)
        fun onPostLikeDoubleClick(post: Post)
        fun onPostCommentClick(post: Post)
        fun onPostShareClick(post: Post)
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
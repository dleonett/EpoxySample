package com.leonett.photofeed.ui.feature.feed

import android.view.View
import com.airbnb.epoxy.*
import com.leonett.photofeed.R
import com.leonett.photofeed.data.model.Post
import com.leonett.photofeed.data.model.Story
import com.leonett.photofeed.ui.adapter.*
import com.leonett.photofeed.ui.viewobject.FeedScreenData

class FeedController(private var onInteractionListener: OnInteractionListener? = null) :
    Typed3EpoxyController<FeedScreenData, Boolean, Boolean>(),
    PostModel.OnInteractionListener {

    override fun buildModels(
        feedScreenData: FeedScreenData,
        loadMore: Boolean,
        isLoading: Boolean
    ) {
        carousel {
            id(STORIES_ID)
            paddingDp(8)
            withModelsFrom(feedScreenData.postsStoriesWrapper.stories) {
                StoryModel_()
                    .id(it.id)
                    .story(it)
                    .itemClickListener { _: View? ->
                        this@FeedController.onInteractionListener?.onStoryClick(it)
                    }
            }
        }

        feedScreenData.postsStoriesWrapper.posts.forEach {
            postHeader {
                id(POST_HEADER_ID + it.id)
                post(it)
                itemClickListener { _: View? ->
                    this@FeedController.onInteractionListener?.onPostAvatarClick(it)
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
                        this@FeedController.onInteractionListener?.onLoadMoreClick()
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

/** Add models to a CarouselModel_ by transforming a list of items into EpoxyModels.
 *
 * @param items The items to transform to models
 * @param modelBuilder A function that take an item and returns a new EpoxyModel for that item.
 */
inline fun <T> CarouselModelBuilder.withModelsFrom(
    items: List<T>,
    modelBuilder: (T) -> EpoxyModel<*>
) {
    models(items.map { modelBuilder(it) })
}

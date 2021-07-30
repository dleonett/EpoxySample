package com.leonett.photofeed.ui.feature.detail.post

import com.airbnb.epoxy.TypedEpoxyController
import com.leonett.photofeed.data.model.Post
import com.leonett.photofeed.ui.adapter.PostModel
import com.leonett.photofeed.ui.adapter.post

class PostDetailController(private var onInteractionListener: OnInteractionListener? = null) :
    TypedEpoxyController<Post>(), PostModel.OnInteractionListener {

    override fun buildModels(post: Post?) {
        post?.let {
            post {
                id(it.id)
                post(it)
                onInteractionListener(this@PostDetailController)
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
        fun onPostLikeClick(post: Post)
        fun onPostLikeDoubleClick(post: Post)
        fun onPostCommentClick(post: Post)
        fun onPostShareClick(post: Post)
    }

    companion object {
        private const val POST_HEADER_ID = "POST_HEADER_ID"
    }
}

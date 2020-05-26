package com.leonett.epoxysample.ui.adapter

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import com.leonett.epoxysample.data.model.Post

class PostsGridController : TypedEpoxyController<List<Post>>() {

    private var onInteractionListener: OnInteractionListener? = null

    override fun buildModels(posts: List<Post>) {
        posts.forEach {
            postsGridItem {
                id(POST_ID + it.id)
                post(it)
                itemClickListener { _: View? ->
                    onInteractionListener?.onPostClick(it)
                }
            }
        }
    }

    fun setOnInteractionListener(onInteractionListener: OnInteractionListener) {
        this.onInteractionListener = onInteractionListener
    }

    interface OnInteractionListener {
        fun onPostClick(post: Post)
    }

    companion object {
        private const val POST_ID = "POST_ID"
    }
}

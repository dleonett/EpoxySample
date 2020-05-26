package com.leonett.epoxysample.ui.feature.profile

import com.airbnb.epoxy.Typed2EpoxyController
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.data.model.User
import com.leonett.epoxysample.ui.adapter.PostsGridModel
import com.leonett.epoxysample.ui.adapter.footer
import com.leonett.epoxysample.ui.adapter.postsGrid
import com.leonett.epoxysample.ui.adapter.profileHeader

class ProfileController : Typed2EpoxyController<User, List<Post>>(),
    PostsGridModel.OnInteractionListener {

    private var onInteractionListener: OnInteractionListener? = null

    override fun buildModels(user: User?, posts: List<Post>) {
        user?.let {
            profileHeader {
                id(HEADER_ID)
                user(it)
            }
        } ?: run {
            footer {
                id(FOOTER_ID)
                titleResId(R.string.profile_user_not_found_text)
            }
        }

        if (posts.isNotEmpty()) {
            postsGrid {
                id(POSTS_GRID_ID)
                posts(posts)
                onInteractionListener(this@ProfileController)
            }
        } else {
            footer {
                id(FOOTER_ID)
                titleResId(R.string.profile_no_posts_text)
            }
        }
    }

    fun setOnItemClickListener(onInteractionListener: OnInteractionListener) {
        this.onInteractionListener = onInteractionListener
    }

    override fun onPostClick(post: Post) {
        onInteractionListener?.onPostClick(post)
    }

    interface OnInteractionListener {
        fun onPostClick(post: Post)
    }

    companion object {
        private const val HEADER_ID = "HEADER_ID"
        private const val POSTS_GRID_ID = "POSTS_GRID_ID"
        private const val FOOTER_ID = "FOOTER_ID"
    }
}

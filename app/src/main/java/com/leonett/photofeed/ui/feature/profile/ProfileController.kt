package com.leonett.photofeed.ui.feature.profile

import com.airbnb.epoxy.Typed3EpoxyController
import com.leonett.photofeed.R
import com.leonett.photofeed.data.model.Post
import com.leonett.photofeed.data.model.User
import com.leonett.photofeed.ui.adapter.*

class ProfileController : Typed3EpoxyController<User, List<Post>, Boolean>(),
    PostsGridModel.OnInteractionListener,
    ProfileHeaderModel.OnInteractionListener {

    private var onInteractionListener: OnInteractionListener? = null

    override fun buildModels(user: User?, posts: List<Post>?, isLoading: Boolean) {
        if (isLoading) {
            loader {
                id(LOADER_ID)
            }
        } else {
            user?.let {
                profileHeader {
                    id(HEADER_ID)
                    user(it)
                    onInteractionListener(this@ProfileController)
                }
            } ?: run {
                footer {
                    id(FOOTER_ID)
                    titleResId(R.string.profile_user_not_found_text)
                }
            }

            if (!posts.isNullOrEmpty()) {
                postsGrid {
                    id(POSTS_GRID_ID)
                    posts(posts)
                    onInteractionListener(this@ProfileController)
                }
            } else {
                val iconResId = when (user?.isPrivate) {
                    true -> R.drawable.ic_locked
                    else -> R.drawable.ic_images
                }
                val titleResId = when (user?.isPrivate) {
                    true -> R.string.profile_private_account_title
                    else -> R.string.profile_no_posts_title
                }
                val subtitleResId = when (user?.isPrivate) {
                    true -> R.string.profile_private_account_subtitle
                    else -> null
                }

                profileInfo {
                    id(INFO_ID)
                    iconResId(iconResId)
                    titleResId(titleResId)
                    subtitleResId(subtitleResId)
                }
            }
        }
    }

    fun setOnItemClickListener(onInteractionListener: OnInteractionListener) {
        this.onInteractionListener = onInteractionListener
    }

    override fun onExternalLinkClick(externalUrl: String) {
        onInteractionListener?.onUserExternalLinkClick(externalUrl)
    }

    override fun onPostClick(post: Post) {
        onInteractionListener?.onPostClick(post)
    }

    interface OnInteractionListener {
        fun onUserExternalLinkClick(externalUrl: String)
        fun onPostClick(post: Post)
    }

    companion object {
        private const val LOADER_ID = "LOADER_ID"
        private const val HEADER_ID = "HEADER_ID"
        private const val POSTS_GRID_ID = "POSTS_GRID_ID"
        private const val FOOTER_ID = "FOOTER_ID"
        private const val INFO_ID = "INFO_ID"
    }
}

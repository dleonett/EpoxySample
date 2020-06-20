package com.leonett.photofeed.ui.feature.detail.post

import com.airbnb.epoxy.TypedEpoxyController
import com.leonett.photofeed.data.model.Post
import com.leonett.photofeed.ui.adapter.post
import com.leonett.photofeed.ui.adapter.postHeader

class PostDetailController : TypedEpoxyController<Post>() {

    override fun buildModels(post: Post?) {
        post?.let {
            postHeader {
                id(POST_HEADER_ID)
                post(it)
            }

            post {
                id(it.id)
                post(it)
            }
        }
    }

    companion object {
        private const val POST_HEADER_ID = "POST_HEADER_ID"
    }
}

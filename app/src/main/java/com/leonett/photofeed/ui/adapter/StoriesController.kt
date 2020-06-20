package com.leonett.photofeed.ui.adapter

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import com.leonett.photofeed.data.model.Story

class StoriesController : TypedEpoxyController<List<Story>>() {

    private var onInteractionListener: OnInteractionListener? = null

    override fun buildModels(items: List<Story>) {
        items.forEach {
            story {
                id(it.id)
                story(it)
                itemClickListener { _: View? ->
                    onInteractionListener?.onStoryClick(it)
                }
            }
        }
    }

    fun setOnInteractionListener(onInteractionListener: OnInteractionListener) {
        this.onInteractionListener = onInteractionListener
    }

    interface OnInteractionListener {
        fun onStoryClick(story: Story)
    }
}
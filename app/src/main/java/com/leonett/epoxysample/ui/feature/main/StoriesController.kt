package com.leonett.epoxysample.ui.feature.main

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import com.leonett.epoxysample.data.model.Story
import com.leonett.epoxysample.ui.adapter.story

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
package com.leonett.epoxysample.ui.adapter

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.*
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.model.Story
import com.leonett.epoxysample.ui.feature.main.StoriesController

@EpoxyModelClass(layout = R.layout.item_stories)
abstract class StoriesModel : EpoxyModelWithHolder<StoriesHolder>(),
    StoriesController.OnInteractionListener {

    @EpoxyAttribute
    var stories: List<Story>? = null
    @EpoxyAttribute
    var onInteractionListener: OnInteractionListener? = null

    override fun bind(holder: StoriesHolder) {
        holder.storiesController.setOnInteractionListener(this)
        holder.storiesController.setData(stories)
    }

    override fun onStoryClick(story: Story) {
        onInteractionListener?.onStoryClick(story)
    }

    interface OnInteractionListener {
        fun onStoryClick(story: Story)
    }
}

class StoriesHolder : EpoxyHolder() {

    lateinit var rvStories: EpoxyRecyclerView
    lateinit var container: View
    lateinit var storiesController: StoriesController

    override fun bindView(itemView: View) {
        rvStories = itemView.findViewById(R.id.rvStories)
        container = itemView

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        storiesController = StoriesController()

        rvStories.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setController(storiesController)
            addItemDecoration(EpoxyItemSpacingDecorator(resources.getDimension(R.dimen.spacing_sm).toInt()))
        }
    }
}
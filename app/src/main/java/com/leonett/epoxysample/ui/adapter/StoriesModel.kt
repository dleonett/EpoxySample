package com.leonett.epoxysample.ui.adapter

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.*
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.Story
import com.leonett.epoxysample.ui.feature.main.StoriesController

@EpoxyModelClass(layout = R.layout.item_stories)
abstract class StoriesModel : EpoxyModelWithHolder<StoriesHolder>() {

    @EpoxyAttribute
    var stories: List<Story>? = null

    override fun bind(holder: StoriesHolder) {
        holder.storiesController.setData(stories)
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
package com.leonett.epoxysample.ui.feature.detail.story

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.model.Story
import com.leonett.epoxysample.ui.base.BaseActivity

class StoryDetailActivity : BaseActivity() {

    override val contentViewId: Int
        get() = R.layout.activity_story_detail

    override fun initVars() {
        val args = Bundle()
        args.putSerializable(
            StoryDetailFragment.STORY_ARGUMENT,
            getArgument(STORY_ARGUMENT) as Story?
        )
        args.putString(
            StoryDetailFragment.STORY_ID_ARGUMENT,
            getArgument(STORY_ID_ARGUMENT) as String?
        )

        findNavController(R.id.navHostFragment).setGraph(R.navigation.story_detail_nav_graph, args)
    }

    override fun initViews() {
        // no-op
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.navHostFragment).navigateUp()

    companion object {
        private const val STORY_ARGUMENT = "story"
        private const val STORY_ID_ARGUMENT = "id"
    }

}
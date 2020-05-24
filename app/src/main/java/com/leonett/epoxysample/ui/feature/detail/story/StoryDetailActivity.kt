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
        val story = getArgument("story") as Story?
        val storyId = getArgument("id") as String?
        showToast("Story: ${story ?: "N/A"} / Id: ${storyId ?: "N/A"}")

        val args = Bundle()
        args.putSerializable("story", story)
        args.putString("id", storyId)

        findNavController(R.id.navHostFragment).setGraph(R.navigation.story_detail_nav_graph, args)
    }

    override fun initViews() {

    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.navHostFragment).navigateUp()

}
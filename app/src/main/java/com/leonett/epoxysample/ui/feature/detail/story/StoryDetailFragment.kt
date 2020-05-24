package com.leonett.epoxysample.ui.feature.detail.story

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.model.Story
import com.leonett.epoxysample.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_story_detail.*

class StoryDetailFragment : BaseFragment() {

    private var story: Story? = null
    private var storyId: String? = null

    override val layoutId: Int
        get() = R.layout.fragment_story_detail

    override fun initVars() {
        arguments?.let {
            story = it.getSerializable(STORY_ARGUMENT) as Story?
            storyId = it.getString(STORY_ID_ARGUMENT)
        }

        showToast(storyId ?: "N/A")
    }

    override fun initViews(view: View) {
        Glide.with(imgAvatar.context)
            .load(story?.imgUrl)
            .apply(RequestOptions().circleCrop())
            .apply(RequestOptions().placeholder(R.drawable.placeholder_image_circle))
            .into(imgAvatar)

        txtUsername.text = story?.username
    }

    companion object {
        private const val STORY_ARGUMENT = "story"
        private const val STORY_ID_ARGUMENT = "id"

        fun createArguments(story: Story): Bundle {
            val bundle = Bundle()
            bundle.putSerializable(STORY_ARGUMENT, story)

            return bundle
        }
    }

}

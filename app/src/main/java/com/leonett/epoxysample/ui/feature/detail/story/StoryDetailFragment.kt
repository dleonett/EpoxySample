package com.leonett.epoxysample.ui.feature.detail.story

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.model.Story
import com.leonett.epoxysample.ui.base.BaseFragment
import com.leonett.epoxysample.ui.feature.profile.ProfileFragment
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

        storyId?.let {
            showToast("ID: $it")
        }
    }

    override fun initViews(view: View) {
        Glide.with(imgAvatar.context)
            .load(story?.imgUrl)
            .apply(RequestOptions().circleCrop())
            .apply(RequestOptions().placeholder(R.drawable.placeholder_image_circle))
            .into(imgAvatar)

        txtUsername.text = story?.username

        story?.userId?.let { userId ->
            imgAvatar.setOnClickListener {
                findNavController().navigate(
                    R.id.actionProfile,
                    ProfileFragment.createArguments(userId)
                )
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onDetach() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onDetach()
    }

    companion object {
        const val STORY_ARGUMENT = "story"
        const val STORY_ID_ARGUMENT = "id"

        fun createArguments(story: Story): Bundle {
            val bundle = Bundle()
            bundle.putSerializable(STORY_ARGUMENT, story)

            return bundle
        }
    }

}

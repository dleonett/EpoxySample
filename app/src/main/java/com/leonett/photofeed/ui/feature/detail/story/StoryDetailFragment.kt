package com.leonett.photofeed.ui.feature.detail.story

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.leonett.photofeed.R
import com.leonett.photofeed.data.model.Story
import com.leonett.photofeed.ui.base.BaseFragment
import com.leonett.photofeed.ui.feature.profile.ProfileFragment
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
            .load(story?.avatarUrl)
            .apply(RequestOptions().circleCrop())
            .apply(RequestOptions().placeholder(R.drawable.placeholder_image_circle))
            .into(imgAvatar)

        Glide.with(imgContent.context)
            .load(story?.imgUrl)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressLoader.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressLoader.visibility = View.GONE
                    return false
                }
            })
            .into(imgContent)

        txtUsername.text = story?.username

        story?.userId?.let { userId ->
            imgAvatar.setOnClickListener {
                findNavController().navigate(
                    R.id.actionProfile,
                    ProfileFragment.createArguments(userId)
                )
            }
        }

        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onDestroyView() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onDestroyView()
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

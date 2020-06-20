package com.leonett.photofeed.ui.feature.detail.post

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonett.photofeed.R
import com.leonett.photofeed.data.model.Post
import com.leonett.photofeed.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_post_detail.*

class PostDetailFragment : BaseFragment() {

    private lateinit var rvController: PostDetailController
    private var post: Post? = null
    private var postId: String? = null

    override val layoutId: Int
        get() = R.layout.fragment_post_detail

    override fun initVars() {
        arguments?.let {
            post = it.getSerializable(POST_ARGUMENT) as Post?
            postId = it.getString(POST_ID_ARGUMENT)
        }
    }

    override fun initViews(view: View) {
        rvController = PostDetailController()

        rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            setController(rvController)
        }

        topBarTitle.text = getString(R.string.post_detail_screen_title)
        btnNavigationUp.setOnClickListener {
            findNavController().navigateUp()
        }

        rvController.setData(post)
    }

    companion object {
        private const val POST_ARGUMENT = "post"
        private const val POST_ID_ARGUMENT = "postId"

        fun createArguments(post: Post): Bundle {
            val bundle = Bundle()
            bundle.putSerializable(POST_ARGUMENT, post)

            return bundle
        }
    }

}
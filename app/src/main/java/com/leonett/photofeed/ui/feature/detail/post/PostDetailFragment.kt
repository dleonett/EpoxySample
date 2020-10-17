package com.leonett.photofeed.ui.feature.detail.post

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonett.photofeed.App
import com.leonett.photofeed.R
import com.leonett.photofeed.data.model.Post
import com.leonett.photofeed.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_post_detail.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PostDetailFragment : BaseFragment(), PostDetailController.OnInteractionListener {

    @Inject
    lateinit var postDetailViewModelFactory: PostDetailViewModelFactory

    private lateinit var postDetailViewModel: PostDetailViewModel
    private var postDetailController = PostDetailController(this)
    private var post: Post? = null
    private var postId: String? = null

    override val layoutId: Int
        get() = R.layout.fragment_post_detail

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireContext().applicationContext as App).appComponent.postDetailComponent().create()
            .inject(this)
    }

    override fun initVars() {
        postDetailViewModel = ViewModelProvider(this, postDetailViewModelFactory)
            .get(PostDetailViewModel::class.java)

        arguments?.let {
            post = it.getSerializable(POST_ARGUMENT) as Post?
            postId = it.getString(POST_ID_ARGUMENT)
        }

        postDetailViewModel.setArguments(post)
    }

    override fun initViews(view: View) {
        rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            setController(postDetailController)
        }

        topBarTitle.text = getString(R.string.post_detail_screen_title)
        btnNavigationUp.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun observeViewModels() {
        postDetailViewModel.getScreenStateLiveData().observe(viewLifecycleOwner, Observer {
            handleScreenState(it)
        })
    }

    private fun handleScreenState(screenState: PostDetailScreenState?) {
        screenState?.let {
            when (it) {
                is PostDetailScreenState.Loading -> {

                }
                is PostDetailScreenState.Success -> {
                    postDetailController.setData(it.post)
                }
                is PostDetailScreenState.Error -> {
                    showToast("Error")
                }
            }
        }
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

    override fun onPostLikeClick(post: Post) {
        postDetailViewModel.onPostLikeClick(post)
    }

    override fun onPostLikeDoubleClick(post: Post) {
        postDetailViewModel.onPostLikeDoubleClick(post)
    }

    override fun onPostCommentClick(post: Post) {
        postDetailViewModel.onPostCommentClick(post)
    }

    override fun onPostShareClick(post: Post) {
        postDetailViewModel.onPostShareClick(post)
    }

}
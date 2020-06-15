package com.leonett.epoxysample.ui.feature.feed

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonett.epoxysample.App
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.data.model.Story
import com.leonett.epoxysample.ui.base.BaseFragment
import com.leonett.epoxysample.ui.feature.detail.story.StoryDetailFragment
import com.leonett.epoxysample.ui.feature.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

class FeedFragment : BaseFragment(), FeedController.OnInteractionListener {

    @Inject
    lateinit var feedViewModelFactory: FeedViewModelFactory

    private lateinit var feedViewModel: FeedViewModel
    private lateinit var feedController: FeedController

    override val layoutId: Int
        get() = R.layout.fragment_feed

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireContext().applicationContext as App).appComponent.feedComponent().create()
            .inject(this)
    }

    override fun initVars() {
        feedViewModel = ViewModelProvider(this, feedViewModelFactory)
            .get(FeedViewModel::class.java)
    }

    override fun initViews(view: View) {
        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeViewModels()
    }

    private fun setupRecyclerView() {
        feedController = FeedController()
        feedController.setOnItemClickListener(this)

        rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            setController(feedController)
        }
    }

    private fun observeViewModels() {
        feedViewModel.getScreenStateLiveData().observe(viewLifecycleOwner, Observer { state ->
            handleScreenState(state)
        })
    }

    private fun handleScreenState(state: FeedScreenState?) {
        state?.let {
            when (it) {
                is FeedScreenState.Loading -> {
                    feedController.setData(it.feedScreenData, it.loadMore, true)
                }
                is FeedScreenState.Success -> {
                    feedController.setData(it.feedScreenData, it.loadMore, false)
                }
                is FeedScreenState.Error -> {
                    feedController.setData(it.feedScreenData, it.loadMore, false)

                    showToast("Error")
                }
            }
        }
    }

    override fun onPostAvatarClick(post: Post) {
        findNavController().navigate(
            R.id.actionProfile,
            ProfileFragment.createArguments(post.userId)
        )
    }

    override fun onPostLikeClick(post: Post) {
        feedViewModel.onPostLikeClick(post)
    }

    override fun onPostLikeDoubleClick(post: Post) {
        feedViewModel.onPostLikeDoubleClick(post)
    }

    override fun onPostCommentClick(post: Post) {
        feedViewModel.onPostCommentClick(post)
    }

    override fun onPostShareClick(post: Post) {
        feedViewModel.onPostShareClick(post)
    }

    override fun onStoryClick(story: Story) {
        findNavController().navigate(
            R.id.actionStoryDetail,
            StoryDetailFragment.createArguments(story)
        )
    }

    override fun onLoadMoreClick() {
        loadMorePosts()
    }

    private fun loadMorePosts() {
        feedViewModel.loadMorePosts()
    }

}
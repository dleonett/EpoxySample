package com.leonett.epoxysample.ui.feature.main

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyItemSpacingDecorator
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.Post
import com.leonett.epoxysample.data.Story
import com.leonett.epoxysample.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),
    MainController.OnInteractionListener {

    private lateinit var mainController: MainController
    private lateinit var mainViewModel: MainViewModel

    override val contentViewId: Int
        get() = R.layout.activity_main

    override fun initVars() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun initViews() {
        setupRecyclerView()
        observeViewModels()
    }

    private fun setupRecyclerView() {
        mainController = MainController()
        mainController.setOnItemClickListener(this)

        rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            setController(mainController)
            addItemDecoration(EpoxyItemSpacingDecorator(resources.getDimension(R.dimen.spacing_sm).toInt()))
        }
    }

    private fun observeViewModels() {
        mainViewModel.getScreenStateLiveData().observe(this, Observer { state ->
            handleScreenState(state)
        })
    }

    private fun handleScreenState(state: MainScreenState?) {
        state?.let {
            when (it) {
                is MainScreenState.Loading -> {
                }
                is MainScreenState.Success -> {
                    mainController.setData(
                        getString(it.titleResId),
                        it.stories,
                        it.posts,
                        it.loadMore
                    )
                }
                is MainScreenState.Error -> {
                }
            }
        }
    }

    override fun onPostClick(post: Post) {
        showToast("Post by ${post.username}")
    }

    override fun onStoryClick(story: Story) {
        showToast("Story by ${story.username}")
    }

    override fun onLoadMoreClick() {
        loadMorePosts()
    }

    private fun loadMorePosts() {
        mainViewModel.loadMorePosts()
    }
}

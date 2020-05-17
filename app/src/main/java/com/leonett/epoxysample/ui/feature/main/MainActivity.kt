package com.leonett.epoxysample.ui.feature.main

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyItemSpacingDecorator
import com.airbnb.epoxy.stickyheader.StickyHeaderLinearLayoutManager
import com.leonett.epoxysample.App
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.data.model.Story
import com.leonett.epoxysample.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(),
    MainController.OnInteractionListener {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainController: MainController

    override val contentViewId: Int
        get() = R.layout.activity_main

    override fun initVars() {
        (applicationContext as App).appComponent.inject(this)

        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory)
            .get(MainViewModel::class.java)
    }

    override fun initViews() {
        setupRecyclerView()
        observeViewModels()
    }

    private fun setupRecyclerView() {
        mainController = MainController()
        mainController.setOnItemClickListener(this)

        rvMain.apply {
            layoutManager = StickyHeaderLinearLayoutManager(context)
            setController(mainController)
            //addItemDecoration(EpoxyItemSpacingDecorator(resources.getDimension(R.dimen.spacing_sm).toInt()))
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
                    mainController.setData(it.mainScreenData, it.loadMore, true)
                }
                is MainScreenState.Success -> {
                    mainController.setData(it.mainScreenData, it.loadMore, false)
                }
                is MainScreenState.Error -> {
                    mainController.setData(it.mainScreenData, it.loadMore, false)

                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
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

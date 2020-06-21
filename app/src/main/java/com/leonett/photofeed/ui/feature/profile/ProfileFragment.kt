package com.leonett.photofeed.ui.feature.profile

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
import com.leonett.photofeed.data.model.UserPostsWrapper
import com.leonett.photofeed.data.model.instagram.IgUser
import com.leonett.photofeed.ui.base.BaseFragment
import com.leonett.photofeed.ui.feature.detail.post.PostDetailFragment
import kotlinx.android.synthetic.main.fragment_post_detail.rvMain
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : BaseFragment(), ProfileController.OnInteractionListener {

    @Inject
    lateinit var feedViewModelFactory: ProfileViewModelFactory

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var profileController: ProfileController
    private var userId: Int? = null
    private var username: String? = null

    override val layoutId: Int
        get() = R.layout.fragment_profile

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireContext().applicationContext as App).appComponent.profileComponent().create()
            .inject(this)
    }

    override fun initVars() {
        profileViewModel = ViewModelProvider(this, feedViewModelFactory)
            .get(ProfileViewModel::class.java)

        arguments?.let {
            userId = it.getInt(USER_ID_ARGUMENT)
            username = it.getString(USERNAME_ARGUMENT)
        }

        profileViewModel.setArguments(userId, username)
    }

    override fun initViews(view: View) {
        profileController = ProfileController()
        profileController.setOnItemClickListener(this)

        rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            setController(profileController)
        }

        btnNavigationUp.setOnClickListener {
            findNavController().navigateUp()
        }

        if (!username.isNullOrEmpty()) {
            topBarTitle.text = username
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeViewModels()
    }

    private fun observeViewModels() {
        profileViewModel.getUserPostsLiveData().observe(viewLifecycleOwner, Observer {
            handleScreenState(it)
        })

        profileViewModel.getUserLiveData().observe(viewLifecycleOwner, Observer {
            handleUser(it)
        })
    }

    private fun handleScreenState(result: UserPostsWrapper?) {
        result?.let {
            topBarTitle.text = it.user.username
            profileController.setData(it.user, it.posts)
        }
    }

    private fun handleUser(result: IgUser?) {
        result?.let {
            topBarTitle.text = it.fullName
        }
    }

    override fun onPostClick(post: Post) {
        findNavController().navigate(
            R.id.actionPostDetail,
            PostDetailFragment.createArguments(post)
        )
    }

    companion object {
        private const val USER_ID_ARGUMENT = "userId"
        private const val USERNAME_ARGUMENT = "username"

        fun createArguments(userId: Int): Bundle {
            val bundle = Bundle()
            bundle.putInt(USER_ID_ARGUMENT, userId)

            return bundle
        }

        fun createArguments(username: String): Bundle {
            val bundle = Bundle()
            bundle.putString(USERNAME_ARGUMENT, username)

            return bundle
        }
    }

}

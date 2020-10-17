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
import com.leonett.photofeed.ui.base.BaseFragment
import com.leonett.photofeed.ui.feature.detail.post.PostDetailFragment
import com.leonett.photofeed.ui.util.addImageAtEnd
import kotlinx.android.synthetic.main.fragment_post_detail.rvMain
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject


class ProfileFragment : BaseFragment(), ProfileController.OnInteractionListener {

    @Inject
    lateinit var profileViewModelFactory: ProfileViewModelFactory

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
        profileViewModel = ViewModelProvider(this, profileViewModelFactory)
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

    override fun observeViewModels() {
        profileViewModel.getProfileScreenStateLiveData().observe(viewLifecycleOwner, Observer {
            handleScreenState(it)
        })
    }

    private fun handleScreenState(state: ProfileScreenState?) {
        state?.let {
            when (it) {
                is ProfileScreenState.Loading -> {
                    profileController.setData(
                        it.userPostsWrapper?.user,
                        it.userPostsWrapper?.posts,
                        true
                    )
                }
                is ProfileScreenState.Success -> {
                    val username = it.userPostsWrapper?.user?.username
                    val isVerified = it.userPostsWrapper?.user?.isVerified == true

                    topBarTitle.text = username

                    if (isVerified) {
                        topBarTitle.addImageAtEnd(R.drawable.ic_verified)
                    }

                    profileController.setData(
                        it.userPostsWrapper?.user,
                        it.userPostsWrapper?.posts,
                        false
                    )
                }
                is ProfileScreenState.Error -> {
                    profileController.setData(
                        it.userPostsWrapper?.user,
                        it.userPostsWrapper?.posts,
                        false
                    )
                    showToast(it.message)
                }
            }
        }
    }

    override fun onUserExternalLinkClick(externalUrl: String) {
        openUrl(externalUrl)
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

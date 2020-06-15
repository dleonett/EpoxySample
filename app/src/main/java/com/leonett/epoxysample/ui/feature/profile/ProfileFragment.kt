package com.leonett.epoxysample.ui.feature.profile

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
import com.leonett.epoxysample.data.model.UserPostsWrapper
import com.leonett.epoxysample.ui.base.BaseFragment
import com.leonett.epoxysample.ui.feature.detail.post.PostDetailFragment
import kotlinx.android.synthetic.main.fragment_post_detail.rvMain
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : BaseFragment(), ProfileController.OnInteractionListener {

    @Inject
    lateinit var feedViewModelFactory: ProfileViewModelFactory

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var profileController: ProfileController
    private var userId: Int? = null

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
        }

        profileViewModel.setArguments(userId ?: 0)
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeViewModels()
    }

    private fun observeViewModels() {
        profileViewModel.getUserPostsLiveData().observe(viewLifecycleOwner, Observer {
            handleScreenState(it)
        })
    }

    private fun handleScreenState(result: UserPostsWrapper?) {
        result?.let {
            topBarTitle.text = it.user.username
            profileController.setData(it.user, it.posts)
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

        fun createArguments(userId: Int): Bundle {
            val bundle = Bundle()
            bundle.putInt(USER_ID_ARGUMENT, userId)

            return bundle
        }
    }

}

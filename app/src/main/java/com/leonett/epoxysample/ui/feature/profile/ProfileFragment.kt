package com.leonett.epoxysample.ui.feature.profile

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.data.model.User
import com.leonett.epoxysample.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_post_detail.*

class ProfileFragment : BaseFragment(), ProfileController.OnInteractionListener {

    private lateinit var profileController: ProfileController
    private var user: User? = null
    private var userId: String? = null

    override val layoutId: Int
        get() = R.layout.fragment_profile

    override fun initVars() {
        arguments?.let {
            user = it.getSerializable(USER_ARGUMENT) as User?
            userId = it.getString(USER_ID_ARGUMENT)
        }
    }

    override fun initViews(view: View) {
        profileController = ProfileController()
        profileController.setOnItemClickListener(this)

        rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            setController(profileController)
        }

        profileController.setData(user, mockPostsList())
    }

    private fun mockPostsList(): List<Post> {
        return Post.generateDummyList()
    }

    override fun onPostClick(post: Post) {
        showToast("post clicked")
    }

    companion object {
        private const val USER_ARGUMENT = "user"

        private const val USER_ID_ARGUMENT = "id"
        fun createArguments(user: User): Bundle {
            val bundle = Bundle()
            bundle.putSerializable(USER_ARGUMENT, user)

            return bundle
        }
    }

}

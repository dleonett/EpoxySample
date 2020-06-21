package com.leonett.photofeed.ui.feature.explore

import android.view.View
import androidx.navigation.fragment.findNavController
import com.leonett.photofeed.R
import com.leonett.photofeed.ui.base.BaseFragment
import com.leonett.photofeed.ui.feature.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_explore.*

class ExploreFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_explore

    override fun initVars() {

    }

    override fun initViews(view: View) {
        btnSearch.setOnClickListener {
            val username = inputUsername.editText?.text.toString()
            navigateToProfile(username)
        }
    }

    private fun navigateToProfile(username: String) {
        findNavController().navigate(R.id.actionProfile, ProfileFragment.createArguments(username))
    }

}

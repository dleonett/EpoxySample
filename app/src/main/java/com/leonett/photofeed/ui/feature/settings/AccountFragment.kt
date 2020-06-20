package com.leonett.photofeed.ui.feature.settings

import android.view.View
import androidx.navigation.fragment.findNavController
import com.leonett.photofeed.R
import com.leonett.photofeed.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_account

    override fun initVars() {

    }

    override fun initViews(view: View) {
        btnOpenAbout.setOnClickListener { navigateToAbout() }
    }

    private fun navigateToAbout() {
        findNavController().navigate(R.id.actionAbout)
    }

}

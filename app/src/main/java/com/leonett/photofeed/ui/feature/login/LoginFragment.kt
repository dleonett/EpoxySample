package com.leonett.photofeed.ui.feature.login

import android.view.View
import androidx.navigation.fragment.findNavController
import com.leonett.photofeed.R
import com.leonett.photofeed.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_login

    override fun initVars() {

    }

    override fun initViews(view: View) {
        btnLogin.setOnClickListener {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.actionHome)
        requireActivity().finish()
    }

}

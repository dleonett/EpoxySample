package com.leonett.photofeed.ui.feature.login

import androidx.navigation.Navigation.findNavController
import com.leonett.photofeed.R
import com.leonett.photofeed.ui.base.BaseActivity

class LoginActivity : BaseActivity() {

    override val contentViewId: Int
        get() = R.layout.activity_login

    override fun initVars() {

    }

    override fun initViews() {

    }

    override fun onSupportNavigateUp() =
        findNavController(this, R.id.navHostFragment).navigateUp()

}
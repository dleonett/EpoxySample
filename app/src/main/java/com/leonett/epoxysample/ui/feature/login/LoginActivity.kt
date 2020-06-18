package com.leonett.epoxysample.ui.feature.login

import androidx.navigation.Navigation.findNavController
import com.leonett.epoxysample.R
import com.leonett.epoxysample.ui.base.BaseActivity

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
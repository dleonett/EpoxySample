package com.leonett.epoxysample.ui.feature.main

import android.content.Intent
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.leonett.epoxysample.R
import com.leonett.epoxysample.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_host.*

class HostActivity : BaseActivity() {

    override val contentViewId: Int
        get() = R.layout.activity_host

    override fun initVars() {

    }

    override fun initViews() {
        val navController = findNavController(this, R.id.navHostFragment)
        bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp() =
        findNavController(this, R.id.navHostFragment).navigateUp()

}
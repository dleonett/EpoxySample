package com.leonett.photofeed.ui.feature.splash

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.leonett.photofeed.R
import com.leonett.photofeed.ui.base.BaseActivity
import com.leonett.photofeed.ui.feature.login.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {

    override val contentViewId: Int
        get() = R.layout.activity_splash

    override fun initVars() {

    }

    override fun initViews() {
        lifecycleScope.launch {
            delay(2000)
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}
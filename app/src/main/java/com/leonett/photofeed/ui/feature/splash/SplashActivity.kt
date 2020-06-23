package com.leonett.photofeed.ui.feature.splash

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.leonett.photofeed.App
import com.leonett.photofeed.R
import com.leonett.photofeed.ui.base.BaseActivity
import com.leonett.photofeed.ui.feature.login.LoginActivity
import com.leonett.photofeed.ui.feature.main.HostActivity
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var splashViewModelFactory: SplashViewModelFactory

    private lateinit var splashViewModel: SplashViewModel

    override val contentViewId: Int
        get() = R.layout.activity_splash

    override fun initVars() {
        (applicationContext as App).appComponent.splashComponent().create()
            .inject(this)

        splashViewModel = ViewModelProvider(this, splashViewModelFactory)
            .get(SplashViewModel::class.java)
    }

    override fun initViews() {
        splashViewModel.getResultLiveData().observe(this, Observer {
            handleResult(it)
        })
    }

    private fun handleResult(it: Boolean?) {
        it?.let { isLoggedIn ->
            if (isLoggedIn) {
                navigateToHome()
            } else {
                navigateToLogin()
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HostActivity::class.java))
        finish()
    }

}
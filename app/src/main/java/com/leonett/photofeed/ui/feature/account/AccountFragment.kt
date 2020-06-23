package com.leonett.photofeed.ui.feature.account

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leonett.photofeed.App
import com.leonett.photofeed.R
import com.leonett.photofeed.ui.base.BaseFragment
import com.leonett.photofeed.ui.feature.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_account.*
import javax.inject.Inject

class AccountFragment : BaseFragment() {

    @Inject
    lateinit var accountViewModelFactory: AccountViewModelFactory

    private lateinit var accountViewModel: AccountViewModel

    override val layoutId: Int
        get() = R.layout.fragment_account

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireContext().applicationContext as App).appComponent.accountComponent().create()
            .inject(this)
    }

    override fun initVars() {
        accountViewModel = ViewModelProvider(this, accountViewModelFactory)
            .get(AccountViewModel::class.java)
    }

    override fun initViews(view: View) {
        btnOpenAbout.setOnClickListener { navigateToAbout() }
        btnSignOut.setOnClickListener { logoutUser() }
    }

    private fun navigateToAbout() {
        findNavController().navigate(R.id.actionAbout)
    }

    private fun logoutUser() {
        accountViewModel.logoutUser()

        navigateToLogin()
    }

    private fun navigateToLogin() {
        val intent = Intent(requireActivity(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}

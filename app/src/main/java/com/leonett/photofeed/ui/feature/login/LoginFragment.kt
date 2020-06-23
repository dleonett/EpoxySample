package com.leonett.photofeed.ui.feature.login

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leonett.photofeed.App
import com.leonett.photofeed.R
import com.leonett.photofeed.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory

    private lateinit var loginViewModel: LoginViewModel

    override val layoutId: Int
        get() = R.layout.fragment_login

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireContext().applicationContext as App).appComponent.loginComponent().create()
            .inject(this)
    }

    override fun initVars() {
        loginViewModel = ViewModelProvider(this, loginViewModelFactory)
            .get(LoginViewModel::class.java)
    }

    override fun initViews(view: View) {
        btnLogin.setOnClickListener {
            loginViewModel.login(
                inputUsername.editText?.text.toString(),
                inputPassword.editText?.text.toString()
            )
            // TODO: 22/06/20 Call Instagram endpoint to check if user exists and only then navigate to Home
            // TODO: 22/06/20 Validate inputs are not empty
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.actionHome)
        requireActivity().finish()
    }

}

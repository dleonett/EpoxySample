package com.leonett.photofeed.ui.feature.login

import android.content.Context
import android.view.View
import androidx.lifecycle.Observer
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
            hideKeyboard()

            loginViewModel.login(
                inputUsername.editText?.text.toString(),
                inputPassword.editText?.text.toString()
            )
        }
    }

    override fun observeViewModels() {
        loginViewModel.getLoginScreenStateLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is LoginScreenState.Loading -> {
                    viewLoader.visibility = View.VISIBLE
                }
                is LoginScreenState.Success -> {
                    viewLoader.visibility = View.GONE
                    navigateToHome()
                }
                is LoginScreenState.Error -> {
                    viewLoader.visibility = View.GONE
                    showSnackbar(btnLogin, it.message)
                }
            }
        })
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.actionHome)
        requireActivity().finish()
    }

}

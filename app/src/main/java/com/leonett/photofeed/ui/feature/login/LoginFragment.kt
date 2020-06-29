package com.leonett.photofeed.ui.feature.login

import android.app.Dialog
import android.content.Context
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leonett.photofeed.App
import com.leonett.photofeed.R
import com.leonett.photofeed.ui.base.BaseFragment
import com.leonett.photofeed.ui.util.LoaderDialog
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loaderDialog: Dialog

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
                    loaderDialog = LoaderDialog().create(requireContext(), getString(R.string.login_loading_message))
                    loaderDialog.show()
                }
                is LoginScreenState.Success -> {
                    loaderDialog.dismiss()
                    navigateToHome()
                }
                is LoginScreenState.Error -> {
                    loaderDialog.dismiss()
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

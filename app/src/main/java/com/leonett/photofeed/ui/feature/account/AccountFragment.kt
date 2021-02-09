package com.leonett.photofeed.ui.feature.account

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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

    override fun observeViewModels() {
        observeSingleStateLiveData()
        observeMultipleLiveData()
    }

    private fun observeSingleStateLiveData() {
        // Screen state
        accountViewModel.screenStateLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                // Loading state
                is AccountScreenState.Loading -> {
                    showProgressDialog("Loading...")
                }

                // Success state
                is AccountScreenState.Success -> {
                    hideProgressDialog()

                    Glide.with(requireContext())
                        .load(it.userInfo.avatarUrl)
                        .apply(RequestOptions().circleCrop())
                        .apply(RequestOptions().placeholder(R.drawable.placeholder_image_circle))
                        .into(imgAvatar)

                    txtName.text = it.userInfo.name
                    txtCity.text = it.userInfo.city

                    btnFriends.text = String.format("Friends (%d)", it.friendsInfo.count)
                    btnFriends.visibility = View.VISIBLE

                    btnPhotos.text = String.format("Photos (%d)", it.photosInfo.count)
                    btnPhotos.visibility = View.VISIBLE
                }

                // Error state
                is AccountScreenState.Error -> {
                    hideProgressDialog()

                    showPersistentSnackbar(txtName, it.message, "Dismiss") {
                        // Just dismiss snack bar
                    }
                }
            }
        })
    }

    private fun observeMultipleLiveData() {
        // Loader
        accountViewModel.loaderLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                showProgressDialog("Loading...")
            } else {
                hideProgressDialog()
            }
        })

        // User info
        accountViewModel.userInfoLiveData.observe(viewLifecycleOwner, Observer {
            Glide.with(requireContext())
                .load(it.avatarUrl)
                .apply(RequestOptions().circleCrop())
                .apply(RequestOptions().placeholder(R.drawable.placeholder_image_circle))
                .into(imgAvatar)

            txtName.text = it.name
            txtCity.text = it.city
        })

        // Friends info
        accountViewModel.friendsInfoLiveData.observe(viewLifecycleOwner, Observer {
            btnFriends.text = String.format("Friends (%d)", it.count)
            btnFriends.visibility = View.VISIBLE
        })

        // Photos info
        accountViewModel.photosInfoLiveData.observe(viewLifecycleOwner, Observer {
            btnPhotos.text = String.format("Photos (%d)", it.count)
            btnPhotos.visibility = View.VISIBLE
        })

        // Error banner
        accountViewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            showPersistentSnackbar(txtName, it, "Dismiss") {
                // Just dismiss snack bar
            }
        })
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

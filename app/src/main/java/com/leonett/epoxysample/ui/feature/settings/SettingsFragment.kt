package com.leonett.epoxysample.ui.feature.settings

import android.view.View
import androidx.navigation.fragment.findNavController
import com.leonett.epoxysample.R
import com.leonett.epoxysample.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_settings

    override fun initVars() {

    }

    override fun initViews(view: View) {
        btnOpenAbout.setOnClickListener { navigateToAbout() }
    }

    private fun navigateToAbout() {
        findNavController().navigate(R.id.actionAbout)
    }

}

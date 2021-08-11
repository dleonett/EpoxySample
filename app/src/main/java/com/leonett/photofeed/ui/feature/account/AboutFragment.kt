package com.leonett.photofeed.ui.feature.account

import android.view.View
import androidx.navigation.fragment.findNavController
import com.leonett.photofeed.R
import com.leonett.photofeed.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_about

    override fun initVars() {

    }

    override fun initViews(view: View) {
        btnNavigationUp.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}

package com.leonett.photofeed.ui.feature.inbox

import android.view.View
import androidx.navigation.fragment.findNavController
import com.leonett.photofeed.R
import com.leonett.photofeed.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_chat

    override fun initVars() {

    }

    override fun initViews(view: View) {
        btnNavigationUp.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}

package com.leonett.photofeed.ui.feature.account

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonett.photofeed.R
import com.leonett.photofeed.data.mapper.SectionsMapper
import com.leonett.photofeed.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : BaseFragment() {

    private lateinit var sectionsController: SectionsController

    override val layoutId: Int
        get() = R.layout.fragment_about

    override fun initVars() {

    }

    override fun initViews(view: View) {
        btnNavigationUp.setOnClickListener {
            findNavController().navigateUp()
        }

        sectionsController = SectionsController()

        rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            setController(sectionsController)
        }

        val response =
            //"[{\"code\":\"004\",\"imageUrl\":\"https://c1.wallpaperflare.com/preview/637/102/522/smell-path-light-fog.jpg\"},{\"code\":\"001\",\"title\":\"Section 001\",\"description\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"},{\"code\":\"002\",\"title\":\"Section 002\",\"buttonText\":\"Click me\"},{\"code\":\"001\",\"title\":\"Section 001\",\"description\":\"This is a very short description, please read carefully\"},{\"code\":\"006\",\"spanCount\":\"2\",\"sections\":[{\"code\":\"002\",\"title\":\"Section 002\",\"buttonText\":\"Click me\"},{\"code\":\"001\",\"title\":\"Section 001\",\"description\":\"This is a very short description, please read carefully\"}]},{\"code\":\"001\",\"title\":\"Section 001\",\"description\":\"This is a very short description, please read carefully\"},{\"code\":\"003\",\"title\":\"Section 003\",\"description\":\"This is a very short description, please read carefully\",\"imageUrl\":\"https://c0.wallpaperflare.com/preview/214/486/567/forest-horizontal-wooden-path-wooden-foot-path.jpg\"},{\"code\":\"002\",\"title\":\"Section 002\",\"buttonText\":\"Action!\"},{\"code\":\"005\",\"buttonText\":\"Go to my profile\"}]"
            "[{\"code\":\"004\",\"imageUrl\":\"https://c1.wallpaperflare.com/preview/637/102/522/smell-path-light-fog.jpg\"},{\"code\":\"006\",\"spanCount\":1,\"sections\":[{\"code\":\"002\",\"title\":\"Section 002\",\"buttonText\":\"Click me\"},{\"code\":\"001\",\"title\":\"Section 001\",\"description\":\"This is a very short description, please read carefully\"}]}]"

        sectionsController.setData(SectionsMapper().getSectionsList(response))
    }

}

package com.leonett.photofeed.ui.feature.account

import android.net.Uri
import android.view.View
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonett.photofeed.R
import com.leonett.photofeed.data.mapper.Section
import com.leonett.photofeed.data.mapper.SectionsMapper
import com.leonett.photofeed.ui.adapter.SectionsController
import com.leonett.photofeed.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : BaseFragment(), SectionsController.ActionListener {

    private lateinit var sectionsController: SectionsController

    override val layoutId: Int
        get() = R.layout.fragment_about

    override fun initVars() {

    }

    override fun initViews(view: View) {
        btnNavigationUp.setOnClickListener {
            findNavController().navigateUp()
        }

        sectionsController =
            SectionsController()
        sectionsController.actionListener = this

        rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            setController(sectionsController)
        }

        val response =
            "[{\"code\":\"004\",\"imageUrl\":\"https://c1.wallpaperflare.com/preview/637/102/522/smell-path-light-fog.jpg\"},{\"code\":\"001\",\"title\":\"Lorem Ipsum\",\"description\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"},{\"code\":\"002\",\"title\":\"Lorem Ipsum\",\"buttonText\":\"Go to feed\",\"action\":{\"type\":\"deeplink\",\"uri\":\"epoxysample://home/feed\"}},{\"code\":\"003\",\"title\":\"Magical woods\",\"description\":\"This is a very short description, please read carefully\",\"imageUrl\":\"https://c0.wallpaperflare.com/preview/214/486/567/forest-horizontal-wooden-path-wooden-foot-path.jpg\"},{\"code\":\"005\",\"buttonText\":\"Go to explore\",\"action\":{\"type\":\"deeplink\",\"uri\":\"epoxysample://home/explore\"}},{\"code\":\"008\",\"imageUrl\":\"https://c1.wallpaperflare.com/preview/637/102/522/smell-path-light-fog.jpg\",\"title\":\"Hello\",\"action\":{\"type\":\"action\",\"code\":\"dashboard\"}},{\"code\":\"006\",\"spanCount\":2,\"sections\":[{\"code\":\"008\",\"title\":\"Tomatoes\",\"imageUrl\":\"https://ychef.files.bbci.co.uk/976x549/p04w46sp.jpg\",\"action\":{\"type\":\"action\",\"code\":\"profile\"}},{\"code\":\"008\",\"title\":\"Onions\",\"imageUrl\":\"https://www.almanac.com/sites/default/files/styles/amp_metadata_content_image_min_696px_wide/public/image_nodes/onions.jpg?itok=2o60drKd\",\"action\":{\"type\":\"action\",\"code\":\"settings\"}}]},{\"code\":\"006\",\"spanCount\":2,\"sections\":[{\"code\":\"003\",\"title\":\"Tomatoes\",\"description\":\"This is a very short description, please read carefully\",\"imageUrl\":\"https://ychef.files.bbci.co.uk/976x549/p04w46sp.jpg\"},{\"code\":\"003\",\"title\":\"Onions\",\"description\":\"This is a very short description, please read carefully\",\"imageUrl\":\"https://www.almanac.com/sites/default/files/styles/amp_metadata_content_image_min_696px_wide/public/image_nodes/onions.jpg?itok=2o60drKd\"}]},{\"code\":\"006\",\"spanCount\":3,\"sections\":[{\"code\":\"002\",\"title\":\"Lorem Ipsum\",\"buttonText\":\"Click me\"},{\"code\":\"001\",\"title\":\"Lorem Ipsum\",\"description\":\"This is a very short description, please read carefully\"},{\"code\":\"006\",\"spanCount\":1,\"sections\":[{\"code\":\"005\",\"buttonText\":\"Go to my profile A\"},{\"code\":\"005\",\"buttonText\":\"Go to my profile B\"}]}]},{\"code\":\"007\",\"spanCount\":1,\"sections\":[{\"code\":\"004\",\"imageUrl\":\"https://c1.wallpaperflare.com/preview/637/102/522/smell-path-light-fog.jpg\"},{\"code\":\"001\",\"title\":\"Lorem Ipsum\",\"description\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"},{\"code\":\"005\",\"buttonText\":\"Go to About\",\"action\":{\"type\":\"deeplink\",\"uri\":\"epoxysample://home/settings\"}}]},{\"code\":\"007\",\"spanCount\":2,\"sections\":[{\"code\":\"004\",\"imageUrl\":\"https://c1.wallpaperflare.com/preview/637/102/522/smell-path-light-fog.jpg\"},{\"code\":\"001\",\"title\":\"Lorem Ipsum\",\"description\":\"Lorem ipsum dolor sit amet.\"}]},{\"code\":\"006\",\"spanCount\":2,\"sections\":[{\"code\":\"007\",\"spanCount\":1,\"sections\":[{\"code\":\"004\",\"imageUrl\":\"https://i.insider.com/5b0584d35e48ec21008b458b?width=1136&format=jpeg\"},{\"code\":\"001\",\"title\":\"Lorem Ipsum\",\"description\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\"},{\"code\":\"005\",\"buttonText\":\"Details\"}]},{\"code\":\"007\",\"spanCount\":1,\"sections\":[{\"code\":\"004\",\"imageUrl\":\"https://i.pinimg.com/originals/2e/e6/10/2ee61025221f347800da512b7c831d08.jpg\"},{\"code\":\"001\",\"title\":\"Lorem Ipsum\",\"description\":\"Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"},{\"code\":\"008\",\"title\":\"Limes\",\"imageUrl\":\"https://techcrunch.com/wp-content/uploads/2015/05/limes-e1431965418433.jpg?w=730&crop=1\",\"action\":{\"type\":\"action\",\"code\":\"store\"}},{\"code\":\"005\",\"buttonText\":\"View more\"}]},{\"code\":\"007\",\"spanCount\":1,\"spanSize\":2,\"sections\":[{\"code\":\"004\",\"imageUrl\":\"https://c1.wallpaperflare.com/preview/637/102/522/smell-path-light-fog.jpg\"},{\"code\":\"001\",\"title\":\"Lorem Ipsum\",\"description\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"code\":\"005\",\"buttonText\":\"Open Inbox\",\"action\":{\"type\":\"deeplink\",\"uri\":\"epoxysample://home/inbox\"}}]}]}]"
        sectionsController.setData(SectionsMapper().getSectionsListAlt(response), false, 0)

        switchIndicators.setOnCheckedChangeListener { _, checked ->
            sectionsController.setData(SectionsMapper().getSectionsListAlt(response), checked, 0)
        }
    }

    override fun onActionPerformed(action: Section.Action) {
        when (action.type) {
            Section.Action.TYPE_DEEPLINK -> {
                navigateToDeepLink(action)
            }
            Section.Action.TYPE_ACTION -> {
                performAction(action)
            }
        }
    }

    private fun navigateToDeepLink(action: Section.Action) {
        val request = NavDeepLinkRequest.Builder
            .fromUri(Uri.parse(action.uri))
            .build()

        try {
            findNavController().navigate(request)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    private fun performAction(action: Section.Action) {
        showToast(action.code ?: "N/A")

        when (action.code) {
            "profile" -> renderSecondResponse()
        }
    }

    private fun renderSecondResponse() {
        val response =
            "[{\"code\":\"004\",\"imageUrl\":\"https://c1.wallpaperflare.com/preview/637/102/522/smell-path-light-fog.jpg\"},{\"code\":\"001\",\"title\":\"Lorem Ipsum\",\"description\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"},{\"code\":\"002\",\"title\":\"Lorem Ipsum\",\"buttonText\":\"Go to feed\",\"action\":{\"type\":\"deeplink\",\"uri\":\"epoxysample://home/feed\"}},{\"code\":\"003\",\"title\":\"Magical woods\",\"description\":\"This is a very short description, please read carefully\",\"imageUrl\":\"https://c0.wallpaperflare.com/preview/214/486/567/forest-horizontal-wooden-path-wooden-foot-path.jpg\"},{\"code\":\"005\",\"buttonText\":\"Go to explore\",\"action\":{\"type\":\"deeplink\",\"uri\":\"epoxysample://home/explore\"}},{\"code\":\"008\",\"imageUrl\":\"https://c1.wallpaperflare.com/preview/637/102/522/smell-path-light-fog.jpg\",\"title\":\"Hello\",\"action\":{\"type\":\"action\",\"code\":\"dashboard\"}},{\"code\":\"006\",\"spanCount\":2,\"sections\":[{\"code\":\"008\",\"title\":\"Tomatoes\",\"imageUrl\":\"https://ychef.files.bbci.co.uk/976x549/p04w46sp.jpg\",\"action\":{\"type\":\"action\",\"code\":\"profile\"}},{\"code\":\"008\",\"title\":\"Onions\",\"imageUrl\":\"https://www.almanac.com/sites/default/files/styles/amp_metadata_content_image_min_696px_wide/public/image_nodes/onions.jpg?itok=2o60drKd\",\"action\":{\"type\":\"action\",\"code\":\"settings\"}}]},{\"code\":\"006\",\"spanCount\":3,\"sections\":[{\"code\":\"002\",\"title\":\"Lorem Ipsum\",\"buttonText\":\"Click me\"},{\"code\":\"001\",\"title\":\"Lorem Ipsum\",\"description\":\"This is a very short description, please read carefully\"},{\"code\":\"006\",\"spanCount\":1,\"sections\":[{\"code\":\"005\",\"buttonText\":\"Go to my profile A\"},{\"code\":\"005\",\"buttonText\":\"Go to my profile B\"}]}]},{\"code\":\"007\",\"spanCount\":1,\"sections\":[{\"code\":\"004\",\"imageUrl\":\"https://c1.wallpaperflare.com/preview/637/102/522/smell-path-light-fog.jpg\"},{\"code\":\"001\",\"title\":\"Lorem Ipsum\",\"description\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"},{\"code\":\"005\",\"buttonText\":\"Go to About\",\"action\":{\"type\":\"deeplink\",\"uri\":\"epoxysample://home/settings\"}}]},{\"code\":\"007\",\"spanCount\":2,\"sections\":[{\"code\":\"004\",\"imageUrl\":\"https://c1.wallpaperflare.com/preview/637/102/522/smell-path-light-fog.jpg\"},{\"code\":\"001\",\"title\":\"Lorem Ipsum\",\"description\":\"Lorem ipsum dolor sit amet.\"}]},{\"code\":\"006\",\"spanCount\":2,\"sections\":[{\"code\":\"007\",\"spanCount\":1,\"sections\":[{\"code\":\"004\",\"imageUrl\":\"https://i.insider.com/5b0584d35e48ec21008b458b?width=1136&format=jpeg\"},{\"code\":\"001\",\"title\":\"Lorem Ipsum\",\"description\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\"},{\"code\":\"005\",\"buttonText\":\"Details\"}]},{\"code\":\"007\",\"spanCount\":1,\"sections\":[{\"code\":\"004\",\"imageUrl\":\"https://i.pinimg.com/originals/2e/e6/10/2ee61025221f347800da512b7c831d08.jpg\"},{\"code\":\"001\",\"title\":\"Lorem Ipsum\",\"description\":\"Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"},{\"code\":\"008\",\"title\":\"Limes\",\"imageUrl\":\"https://techcrunch.com/wp-content/uploads/2015/05/limes-e1431965418433.jpg?w=730&crop=1\",\"action\":{\"type\":\"action\",\"code\":\"store\"}},{\"code\":\"005\",\"buttonText\":\"View more\"}]},{\"code\":\"007\",\"spanCount\":1,\"spanSize\":2,\"sections\":[{\"code\":\"004\",\"imageUrl\":\"https://c1.wallpaperflare.com/preview/637/102/522/smell-path-light-fog.jpg\"},{\"code\":\"001\",\"title\":\"Lorem Ipsum\",\"description\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"code\":\"005\",\"buttonText\":\"Open Inbox\",\"action\":{\"type\":\"deeplink\",\"uri\":\"epoxysample://home/inbox\"}}]}]}]"
        sectionsController.setData(SectionsMapper().getSectionsListAlt(response), switchIndicators.isChecked, 0)
    }

}

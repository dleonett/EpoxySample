package com.leonett.photofeed.ui.feature.main

import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.leonett.photofeed.R
import com.leonett.photofeed.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_host.*

class HostActivity : BaseActivity() {

    override val contentViewId: Int
        get() = R.layout.activity_host

    override fun initVars() {

    }

    override fun initViews() {
        val navController = findNavController(this, R.id.navHostFragment)

        // Show/hide bottomNavigationView based on active destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNav.visibility =
                if (destination.id == R.id.chatFragment
                    || destination.id == R.id.storyDetailFragment
                ) View.GONE else View.VISIBLE
        }

        bottomNav.setupWithNavController(navController)

        // Prevent destination reselection if destination is already active
        // Navigate to nav_graph root instead
        bottomNav.setOnNavigationItemReselectedListener {
            val id = when (it.itemId) {
                R.id.explore_nav_graph -> R.id.exploreFragment
                R.id.inbox_nav_graph -> R.id.inboxFragment
                R.id.settings_nav_graph -> R.id.settingsFragment
                else -> it.itemId
            }
            navController.popBackStack(id, false)
        }
    }

    override fun onSupportNavigateUp() =
        findNavController(this, R.id.navHostFragment).navigateUp()

}
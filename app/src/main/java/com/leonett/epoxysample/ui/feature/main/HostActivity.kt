package com.leonett.epoxysample.ui.feature.main

import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.leonett.epoxysample.R
import com.leonett.epoxysample.ui.base.BaseActivity
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
            if (destination.id == R.id.chatFragment) {
                bottomNav.visibility = View.GONE
            } else {
                bottomNav.visibility = View.VISIBLE
            }
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
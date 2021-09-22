package com.leonett.photofeed.ui.compose.screen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.leonett.photofeed.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object List : Screen("list", R.string.bottom_nav_item_list, Icons.Filled.List)
    object Favorites : Screen("favorites", R.string.bottom_nav_item_favorites, Icons.Filled.Favorite)
    object Profile : Screen("profile", R.string.bottom_nav_item_profile, Icons.Filled.Person)
}

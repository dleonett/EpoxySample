package com.leonett.photofeed.ui.compose.screen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.leonett.photofeed.R

sealed class NavigationItem(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object List : NavigationItem("list", R.string.bottom_nav_item_list, Icons.Filled.List)
    object Favorites : NavigationItem(
        "favorites",
        R.string.bottom_nav_item_favorites,
        Icons.Filled.Favorite
    )

    object Profile :
        NavigationItem("profile", R.string.bottom_nav_item_profile, Icons.Filled.Person)
}

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    val deepLink: String? = null
) {
    object List : Screen("list", R.string.bottom_nav_item_list, Icons.Filled.List)
    object FavoritesList :
        Screen(
            "favorites/list",
            R.string.bottom_nav_item_favorites,
            Icons.Filled.Favorite,
            "favorites"
        )

    object FavoritesDetail :
        Screen(
            "favorites/detail",
            R.string.bottom_nav_item_favorites,
            Icons.Filled.Favorite,
            "favorites/detail"
        )

    object Profile : Screen("profile", R.string.bottom_nav_item_profile, Icons.Filled.Person)
}

sealed class Graph(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    val screens: List<Screen>
) {
    object Favorites : Graph(
        "favorites", R.string.bottom_nav_item_favorites,
        Icons.Filled.Favorite, listOf(Screen.FavoritesList, Screen.FavoritesDetail)
    )
}

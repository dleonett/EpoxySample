package com.leonett.photofeed.ui.compose.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import androidx.navigation.navDeepLink
import com.leonett.photofeed.ui.compose.constants.Navigation

@Composable
fun MainScreen() {
    val bottomNavigationItems = listOf(
        NavigationItem.List,
        NavigationItem.Favorites,
        NavigationItem.Profile
    )

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Hello!") })
        },
        bottomBar = {
            BottomAppBar(contentPadding = PaddingValues(0.dp)) {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    bottomNavigationItems.forEach { item ->
                        BottomNavigationItem(
                            icon = { Icon(imageVector = item.icon, contentDescription = null) },
                            label = { Text(stringResource(id = item.resourceId)) },
                            selected = currentDestination?.hierarchy?.any {
                                it.route == item.route || (it.route?.contains(
                                    "${item.route}/"
                                ) == true)
                            } == true,
                            onClick = {
                                navController.navigate(item.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }) {
        NavHost(
            navController = navController,
            startDestination = Screen.List.route
        ) {
            composable(Screen.List.route) { ListScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
            navigation(startDestination = Screen.FavoritesList.route, route = Graph.Favorites.route) {
                composable(Screen.FavoritesList.route, deepLinks = listOf(
                    navDeepLink { uriPattern = "${Navigation.URI_BASE}${Screen.FavoritesList.deepLink}" }
                )) { FavoritesScreen(navController) }
                composable(Screen.FavoritesDetail.route, deepLinks = listOf(
                    navDeepLink { uriPattern = "${Navigation.URI_BASE}${Screen.FavoritesDetail.deepLink}" }
                )) { DetailScreen() }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}
package com.leonett.photofeed.ui.compose.screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val bottomNavigationItems = listOf(
        Screen.List,
        Screen.Favorites,
        Screen.Profile
    )

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Hello world!") })
        },
        bottomBar = {
            BottomAppBar {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    bottomNavigationItems.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                            label = { Text(stringResource(id = screen.resourceId)) },
                            selected = currentDestination?.hierarchy?.any {
                                it.route == screen.route || (it.route?.contains(
                                    "${screen.route}/"
                                ) == true)
                            } == true,
                            onClick = {
                                navController.navigate(screen.route) {
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
            composable(Screen.Favorites.route) { FavoritesScreen(navController) }
            composable("favorites/detail") { DetailScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}
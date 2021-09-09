package com.leonett.photofeed.ui.compose.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen() {
    val bottomNavigationItems = listOf(
        BottomNavigationScreens.List,
        BottomNavigationScreens.Favorites,
        BottomNavigationScreens.Profile
    )

    val openDialog = remember { mutableStateOf<BottomNavigationScreens?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Hello world!") })
        },
        bottomBar = {
            BottomAppBar {
                BottomNavigation {
                    bottomNavigationItems.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                            label = { Text(stringResource(id = screen.resourceId)) },
                            selected = false,
                            onClick = { openDialog.value = screen }
                        )
                    }
                }
            }
        }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // content
        }
    }

    openDialog.value?.let { screen ->
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
                openDialog.value = null
            },
            title = {
                Text(text = stringResource(screen.resourceId))
            },
            text = { Text("Here is a text ") },
            confirmButton = {
                TextButton(onClick = { openDialog.value = null }) {
                    Text("This is the Confirm Button")
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog.value = null }) {
                    Text("This is the Dismiss Button")
                }
            }
        )
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}
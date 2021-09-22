package com.leonett.photofeed.ui.compose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FavoritesScreen(navController: NavController?) {
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {
        Text(text = "Favorites")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController?.navigate("favorites/detail") }) {
            Text(text = "Level 2")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen(null)
}
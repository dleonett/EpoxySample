package com.leonett.photofeed.ui.compose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListScreen() {
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {
        Text(text = "List")
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    ListScreen()
}
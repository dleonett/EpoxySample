package com.leonett.photofeed.ui.compose.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.leonett.photofeed.ui.compose.constants.Colors

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(
            primary = Colors.ORANGE
        )
    ) {
        content()
    }
}
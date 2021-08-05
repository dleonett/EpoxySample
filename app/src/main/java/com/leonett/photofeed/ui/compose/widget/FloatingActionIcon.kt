package com.leonett.photofeed.ui.compose.widget

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FloatingActionIcon(iconId: String? = null) {
    iconId?.let {
        var iconSource: ImageVector? = null
        when (it) {
            "add" -> iconSource = Icons.Default.Add
            "done" -> iconSource = Icons.Default.Done
            "close" -> iconSource = Icons.Default.Close
            "refresh" -> iconSource = Icons.Default.Refresh
            "share" -> iconSource = Icons.Default.Share
            "back" -> iconSource = Icons.Default.ArrowBack
            "user" -> iconSource = Icons.Default.Person
        }

        iconSource?.let { source ->
            Icon(
                imageVector = source,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun PreviewFloatingActionIcon() {
    FloatingActionIcon("add")
}
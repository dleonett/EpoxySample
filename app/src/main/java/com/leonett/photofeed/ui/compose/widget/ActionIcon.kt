package com.leonett.photofeed.ui.compose.widget

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.leonett.photofeed.data.mapper.Action
import com.leonett.photofeed.data.mapper.Icon

@Composable
fun ActionIcon(icon: Icon? = null, onActionClick: ((action: Action) -> Unit)? = null) {
    icon?.iconId.let { iconId ->
        var iconSource: ImageVector? = null
        when (iconId) {
            "add" -> iconSource = Icons.Default.Add
            "done" -> iconSource = Icons.Default.Done
            "close" -> iconSource = Icons.Default.Close
            "refresh" -> iconSource = Icons.Default.Refresh
            "share" -> iconSource = Icons.Default.Share
            "back" -> iconSource = Icons.Default.ArrowBack
            "user" -> iconSource = Icons.Default.Person
        }

        iconSource?.let { source ->
            IconButton(onClick = {
                icon?.action?.let {
                    onActionClick?.invoke(it)
                }
            }) {
                Icon(
                    imageVector = source,
                    contentDescription = null
                )
            }
        }
    }
}
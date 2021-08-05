package com.leonett.photofeed.ui.compose.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.leonett.photofeed.R
import com.leonett.photofeed.data.mapper.Action
import com.leonett.photofeed.data.mapper.Contact

@Composable
fun Contact(contact: Contact, onActionClick: ((action: Action) -> Unit)? = null) {
    Column(modifier = Modifier
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            contact.action?.let {
                onActionClick?.invoke(it)
            }
        }
        .width(64.dp)) {
        Box(modifier = Modifier.size(64.dp)) {
            Image(
                painter = rememberImagePainter(contact.avatarUrl,
                    builder = {
                        placeholder(R.drawable.placeholder_image_circle)
                        transformations(CircleCropTransformation())
                    }),
                contentDescription = "User profile image",
                modifier = Modifier.fillMaxSize()
            )
            if (contact.type != "none") {
                val icon = when (contact.type) {
                    "local" -> Icons.Default.Person
                    else -> Icons.Default.Home
                }
                Surface(
                    elevation = 2.dp,
                    shape = CircleShape,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(4.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = contact.username,
            style = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center),
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContact() {
    Contact(Contact.mock())
}
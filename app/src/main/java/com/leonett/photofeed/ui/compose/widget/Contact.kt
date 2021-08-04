package com.leonett.photofeed.ui.compose.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.leonett.photofeed.R
import com.leonett.photofeed.data.model.Contact

@Composable
fun Contact(contact: Contact, onContactClick: ((contact: Contact) -> Unit)? = null) {
    Column(modifier = Modifier
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) { onContactClick?.invoke(contact) }
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
            if (contact.type != Contact.ContactType.NONE) {
                val resId = when (contact.type) {
                    Contact.ContactType.LOCAL -> R.drawable.ic_account
                    else -> R.drawable.ic_home
                }

                Image(
                    painter = painterResource(id = resId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
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
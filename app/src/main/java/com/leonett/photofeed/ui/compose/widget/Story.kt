package com.leonett.photofeed.ui.compose.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.leonett.photofeed.R
import com.leonett.photofeed.data.model.Story
import com.leonett.photofeed.ui.compose.constants.Colors

@Composable
fun Story(story: Story, onStoryClick: ((story: Story) -> Unit)? = null) {
    Column(modifier = Modifier
        .clickable { onStoryClick?.invoke(story) }
        .width(64.dp)) {
        Image(
            painter = rememberImagePainter(story.avatarUrl,
                builder = {
                    placeholder(R.drawable.placeholder_image_circle)
                    transformations(CircleCropTransformation())
                }),
            contentDescription = "User profile image",
            modifier = Modifier
                .size(64.dp)
                .border(2.dp, Colors.ORANGE, CircleShape)
                .padding(4.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = story.username,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStory() {
    Story(Story.mock())
}
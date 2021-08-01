package com.leonett.photofeed.ui.compose.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leonett.photofeed.data.model.Story

@Composable
fun Stories(stories: List<Story>, onStoryClick: ((story: Story) -> Unit)? = null) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(stories) { story ->
            Story(story = story, onStoryClick = onStoryClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStories() {
    Stories(Story.generateDummyList())
}
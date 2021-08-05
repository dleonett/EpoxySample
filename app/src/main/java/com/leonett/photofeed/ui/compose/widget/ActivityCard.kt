package com.leonett.photofeed.ui.compose.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leonett.photofeed.data.mapper.Action
import com.leonett.photofeed.data.mapper.ActivityCard

@Composable
fun ActivityCard(activityCard: ActivityCard, onActionClick: ((action: Action) -> Unit)? = null) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                activityCard.action?.let {
                    onActionClick?.invoke(it)
                }
            },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = activityCard.title, style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = activityCard.subtitle, style = TextStyle(fontSize = 14.sp))
        }
    }
}
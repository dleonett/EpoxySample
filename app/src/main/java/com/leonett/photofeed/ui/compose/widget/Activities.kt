package com.leonett.photofeed.ui.compose.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leonett.photofeed.data.mapper.Action
import com.leonett.photofeed.data.mapper.ActivityCard

@Composable
fun Activities(activities: List<ActivityCard>, onActionClick: ((action: Action) -> Unit)? = null) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(horizontal = 16.dp, vertical = 8.dp)),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        activities.forEach {
            ActivityCard(
                activityCard = it,
                onActionClick = onActionClick
            )
        }
    }
}

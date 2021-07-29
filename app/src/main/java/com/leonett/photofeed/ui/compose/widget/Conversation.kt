package com.leonett.photofeed.ui.compose.widget

import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.leonett.photofeed.data.model.Message
import com.leonett.photofeed.data.model.User
import com.leonett.photofeed.util.toDate
import com.leonett.photofeed.data.model.Conversation as ConversationModel

@Composable
fun Conversation(conversation: ConversationModel, onClick: (() -> Unit)? = null) {
    Row(modifier = Modifier
        .clickable { onClick?.invoke() }
        .padding(PaddingValues(horizontal = 16.dp, vertical = 12.dp))) {
        Image(
            painter = rememberImagePainter(conversation.user.avatarUrl),
            contentDescription = "User profile image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Row {
                Text(
                    text = conversation.user.displayName,
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .weight(1f)
                )
                Text(
                    text = DateUtils.getRelativeTimeSpanString(
                        conversation.lastMessage?.timestamp?.toDate()?.time ?: 0,
                        System.currentTimeMillis(),
                        DateUtils.MINUTE_IN_MILLIS
                    ).toString(),
                    style = TextStyle(color = Color(0xFF9F9F9F), fontSize = 14.sp)
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = conversation.lastMessage?.body.orEmpty(),
                style = TextStyle(fontSize = 14.sp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConversation() {
    MaterialTheme {
        Conversation(conversation = ConversationModel("1", User.mock(), Message.mock()))
    }
}
package com.leonett.photofeed.ui.compose.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.leonett.photofeed.R
import com.leonett.photofeed.data.model.Post
import com.leonett.photofeed.util.formatWithSeparators

@Composable
fun Post(post: Post) {
    Column {
        PostHeader(post = post)
        PostContent(post = post)
        PostFooter(post = post)
    }
}

@Composable
fun PostHeader(post: Post) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(PaddingValues(horizontal = 16.dp, vertical = 8.dp))
        ) {
            Image(
                painter = rememberImagePainter(post.avatarUrl),
                contentDescription = "User profile image",
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = post.username.orEmpty(),
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                )
                if (!post.location.isNullOrEmpty()) {
                    Text(
                        text = post.location.orEmpty(),
                        style = TextStyle(fontSize = 12.sp)
                    )
                }
            }
        }
    }
}

@Composable
fun PostContent(post: Post) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberImagePainter(post.imgUrl),
            contentDescription = "Content",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PostFooter(post: Post) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(horizontal = 16.dp, vertical = 8.dp))
    ) {
        Row {
            val likeIconRes = if (post.likedByMe) R.drawable.ic_like_off else R.drawable.ic_like_on
            Image(
                painter = painterResource(id = likeIconRes),
                contentDescription = "Like button",
                modifier = Modifier
                    .size(32.dp)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = "Comment button",
                modifier = Modifier
                    .size(32.dp)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = "Share button",
                modifier = Modifier
                    .size(32.dp)
                    .padding(4.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.post_likes, post.likes.formatWithSeparators()),
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = post.title.orEmpty(),
            style = TextStyle(fontSize = 14.sp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = stringResource(
                id = R.string.post_comments,
                post.comments.formatWithSeparators()
            ),
            style = TextStyle(fontSize = 14.sp, color = Color(0xFF9F9F9F))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPostHeader() {
    PostHeader(Post.mock())
}

@Preview(showBackground = true)
@Composable
fun PreviewPostContent() {
    PostContent(Post.mock())
}

@Preview(showBackground = true)
@Composable
fun PreviewPostFooter() {
    PostFooter(Post.mock())
}
package com.leonett.photofeed.ui.compose.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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

@ExperimentalFoundationApi
@Composable
fun Post(
    post: Post,
    onPostAvatarClick: ((post: Post) -> Unit)? = null,
    onPostMoreClick: ((post: Post) -> Unit)? = null,
    onPostLikeClick: ((post: Post) -> Unit)? = null,
    onPostCommentClick: ((post: Post) -> Unit)? = null,
    onPostShareClick: ((post: Post) -> Unit)? = null,
    onPostContentDoubleClick: ((post: Post) -> Unit)? = null
) {
    Column {
        PostHeader(
            post = post,
            onPostAvatarClick = onPostAvatarClick,
            onPostMoreClick = onPostMoreClick
        )
        PostContent(post = post, onPostContentDoubleClick = onPostContentDoubleClick)
        PostFooter(
            post = post, onPostLikeClick = onPostLikeClick, onPostCommentClick = onPostCommentClick,
            onPostShareClick = onPostShareClick
        )
    }
}

@Composable
fun PostHeader(
    post: Post,
    onPostAvatarClick: ((post: Post) -> Unit)? = null,
    onPostMoreClick: ((post: Post) -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .padding(PaddingValues(horizontal = 16.dp, vertical = 8.dp))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(post.avatarUrl,
                builder = { placeholder(R.drawable.placeholder_image_circle) }),
            contentDescription = "User profile image",
            modifier = Modifier
                .clickable { onPostAvatarClick?.invoke(post) }
                .size(36.dp)
                .clip(CircleShape)
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1F)
        ) {
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
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "More button",
            modifier = Modifier
                .clickable { onPostMoreClick?.invoke(post) }
                .size(36.dp)
                .padding(4.dp)
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun PostContent(post: Post, onPostContentDoubleClick: ((post: Post) -> Unit)? = null) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberImagePainter(
                data = post.imgUrl,
                builder = {
                    placeholder(R.color.gray_light)
                }),
            contentDescription = "Content",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1F)
                .combinedClickable(
                    onClick = {},
                    onDoubleClick = { onPostContentDoubleClick?.invoke(post) }),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun PostFooter(
    post: Post,
    onPostLikeClick: ((post: Post) -> Unit)? = null,
    onPostCommentClick: ((post: Post) -> Unit)? = null,
    onPostShareClick: ((post: Post) -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(horizontal = 16.dp, vertical = 8.dp))
    ) {
        Row {
            val likeIconRes = if (post.likedByMe) R.drawable.ic_like_on else R.drawable.ic_like_off
            Image(
                painter = painterResource(id = likeIconRes),
                contentDescription = "Like button",
                modifier = Modifier
                    .clickable { onPostLikeClick?.invoke(post) }
                    .size(32.dp)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = "Comment button",
                modifier = Modifier
                    .clickable { onPostCommentClick?.invoke(post) }
                    .size(32.dp)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = "Share button",
                modifier = Modifier
                    .clickable { onPostShareClick?.invoke(post) }
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
            style = TextStyle(fontSize = 14.sp, color = Color(0xFF808080))
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = post.timeAgo,
            style = TextStyle(fontSize = 10.sp, color = Color(0xFF808080))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPostHeader() {
    PostHeader(Post.mock())
}

@ExperimentalFoundationApi
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
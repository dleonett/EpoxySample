package com.leonett.photofeed.ui.compose.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.leonett.photofeed.ui.compose.constants.Dimens

@Composable
fun Header(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(Dimens.TOOLBAR_HEIGHT)
    ) {
        Text(
            text = title,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun PreviewHeader() {
    Header("Title")
}
package com.leonett.photofeed.ui.compose.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ContainerWithHeader(headerTitle: String = "", content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxHeight()) {
        Header(title = headerTitle)
        Divider(color = Color(0x22000000))
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContainerWithHeader() {
    ContainerWithHeader(headerTitle = "Header") {
        Text(text = "Content")
    }
}
package com.leonett.photofeed.data.mapper

data class ComposableScreen(
    val title: String? = null,
    val floatingAction: FloatingAction? = null,
    val sections: List<Section>
)
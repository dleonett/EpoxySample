package com.leonett.photofeed.data.mapper

data class ComposableScreen(
    val topBar: TopBar? = null,
    val floatingAction: FloatingAction? = null,
    val sections: List<Section>
)

data class Icon(val iconId: String, val action: Action? = null)

data class TopBar(
    val title: String? = null,
    val navIcon: Icon? = null,
    val actions: List<Icon>? = null
)

data class FloatingAction(val text: String, val iconId: String? = null, val action: Action? = null)
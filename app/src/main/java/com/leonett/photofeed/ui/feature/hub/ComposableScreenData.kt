package com.leonett.photofeed.ui.feature.hub

import com.leonett.photofeed.data.mapper.FloatingAction
import com.leonett.photofeed.data.mapper.Section
import com.leonett.photofeed.data.mapper.TopBar

data class ComposableScreenData(
    val topBar: TopBar? = null,
    val floatingAction: FloatingAction? = null,
    val errorMessage: String? = null,
    val sections: List<Section>? = null
)
package com.leonett.photofeed.ui.feature.hub

import com.leonett.photofeed.data.mapper.FloatingAction
import com.leonett.photofeed.data.mapper.Section

data class ComposableScreenData(
    val screenTitle: String,
    val floatingAction: FloatingAction? = null,
    val errorMessage: String? = null,
    val sections: List<Section>? = null
)
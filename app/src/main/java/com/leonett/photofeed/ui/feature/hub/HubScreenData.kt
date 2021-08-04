package com.leonett.photofeed.ui.feature.hub

import com.leonett.photofeed.data.mapper.Section

data class HubScreenData(
    val screenTitle: String,
    val sections: List<Section>? = null
)
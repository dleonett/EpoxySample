package com.leonett.photofeed.ui.feature.hub

import com.leonett.photofeed.data.mapper.Container

data class ComposableScreenData(
    val containers: List<Container>? = null,
    val errorMessage: String? = null
)

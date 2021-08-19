package com.leonett.photofeed.ui.compose.extensions

import com.leonett.photofeed.data.mapper.Container
import com.leonett.photofeed.data.mapper.Section

inline fun <reified T : Any> getContainerByType(containers: List<Container>?) =
    castContainer<T>(containers?.firstOrNull { it is T })

inline fun <reified T : Any> castContainer(any: Container?): T? = T::class.javaObjectType.cast(any)

inline fun <reified T : Any> getSectionByType(sections: List<Section>?) =
    castSection<T>(sections?.firstOrNull { it is T })

inline fun <reified T : Any> castSection(any: Section?): T? = T::class.javaObjectType.cast(any)
package com.leonett.photofeed.ui.feature.account

import com.airbnb.epoxy.TypedEpoxyController
import com.leonett.photofeed.data.mapper.*
import com.leonett.photofeed.ui.adapter.*

class AboutController : TypedEpoxyController<List<Section>>() {

    override fun buildModels(sections: List<Section>) {
        sections.forEachIndexed { index, section ->
            when (section) {
                is Section001 -> {
                    section001 {
                        id("${section.code} $index")
                        section(section)
                    }
                }
                is Section002 -> {
                    section002 {
                        id("${section.code} $index")
                        section(section)
                    }
                }
                is Section003 -> {
                    section003 {
                        id("${section.code} $index")
                        section(section)
                    }
                }
                is Section004 -> {
                    section004 {
                        id("${section.code} $index")
                        section(section)
                    }
                }
                is Section005 -> {
                    section005 {
                        id("${section.code} $index")
                        section(section)
                    }
                }
            }
        }
    }
}
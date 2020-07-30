package com.leonett.photofeed.ui.feature.account

import com.airbnb.epoxy.Typed2EpoxyController
import com.leonett.photofeed.data.mapper.*
import com.leonett.photofeed.ui.adapter.*

class SectionsController : Typed2EpoxyController<List<Section>, Boolean>() {

    override fun buildModels(sections: List<Section>, showIndicators: Boolean) {
        sections.forEachIndexed { index, section ->
            when (section) {
                is Section001 -> {
                    section001 {
                        id("${section.code} $index")
                        section(section)
                        showIndicators(showIndicators)
                    }
                }
                is Section002 -> {
                    section002 {
                        id("${section.code} $index")
                        section(section)
                        showIndicators(showIndicators)
                    }
                }
                is Section003 -> {
                    section003 {
                        id("${section.code} $index")
                        section(section)
                        showIndicators(showIndicators)
                    }
                }
                is Section004 -> {
                    section004 {
                        id("${section.code} $index")
                        section(section)
                        showIndicators(showIndicators)
                    }
                }
                is Section005 -> {
                    section005 {
                        id("${section.code} $index")
                        section(section)
                        showIndicators(showIndicators)
                    }
                }
                is Section006 -> {
                    section006 {
                        id("${section.code} $index")
                        section(section)
                        showIndicators(showIndicators)
                    }
                }
            }
        }
    }
}
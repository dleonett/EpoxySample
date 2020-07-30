package com.leonett.photofeed.ui.adapter

import com.airbnb.epoxy.Typed3EpoxyController
import com.leonett.photofeed.data.mapper.*

class SectionsController : Typed3EpoxyController<List<Section>, Boolean, Int>() {

    override fun buildModels(sections: List<Section>, showIndicators: Boolean, level: Int) {
        sections.forEachIndexed { index, section ->
            when (section) {
                is Section001 -> {
                    section001 {
                        id(section.code + section.id + level)
                        section(section)
                        showIndicators(showIndicators)
                    }
                }
                is Section002 -> {
                    section002 {
                        id(section.code + section.id + level)
                        section(section)
                        showIndicators(showIndicators)
                    }
                }
                is Section003 -> {
                    section003 {
                        id(section.code + section.id + level)
                        section(section)
                        showIndicators(showIndicators)
                    }
                }
                is Section004 -> {
                    section004 {
                        id(section.code + section.id + level)
                        section(section)
                        showIndicators(showIndicators)
                    }
                }
                is Section005 -> {
                    section005 {
                        id(section.code + section.id + level)
                        section(section)
                        showIndicators(showIndicators)
                    }
                }
                is Section006 -> {
                    section006 {
                        id(section.code + section.id + level)
                        section(section)
                        showIndicators(showIndicators)
                        parentLevel(level)
                    }
                }
                is Section007 -> {
                    section007 {
                        id(section.code + section.id + level)
                        section(section)
                        showIndicators(showIndicators)
                        parentLevel(level)
                    }
                }
            }
        }
    }
}
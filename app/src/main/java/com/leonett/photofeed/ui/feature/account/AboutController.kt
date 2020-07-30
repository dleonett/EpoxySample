package com.leonett.photofeed.ui.feature.account

import com.airbnb.epoxy.TypedEpoxyController
import com.leonett.photofeed.data.mapper.*
import com.leonett.photofeed.ui.adapter.section001
import com.leonett.photofeed.ui.adapter.section002
import com.leonett.photofeed.ui.adapter.section003
import com.leonett.photofeed.ui.adapter.section004

class AboutController : TypedEpoxyController<List<Section>>() {

    override fun buildModels(sections: List<Section>) {
        sections.forEachIndexed { index, section ->
            when (section) {
                is Section001 -> {
                    section001 {
                        id("$SECTION_001_ID $index")
                        section(section)
                    }
                }
                is Section002 -> {
                    section002 {
                        id("$SECTION_002_ID $index")
                        section(section)
                    }
                }
                is Section003 -> {
                    section003 {
                        id("$SECTION_003_ID $index")
                        section(section)
                    }
                }
                is Section004 -> {
                    section004 {
                        id("$SECTION_004_ID $index")
                        section(section)
                    }
                }
            }
        }
    }

    companion object {
        private const val SECTION_001_ID = "SECTION_001_ID"
        private const val SECTION_002_ID = "SECTION_002_ID"
        private const val SECTION_003_ID = "SECTION_003_ID"
        private const val SECTION_004_ID = "SECTION_004_ID"
    }
}
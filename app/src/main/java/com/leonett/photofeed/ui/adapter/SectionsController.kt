package com.leonett.photofeed.ui.adapter

import com.airbnb.epoxy.Typed3EpoxyController
import com.leonett.photofeed.data.mapper.*

class SectionsController : Typed3EpoxyController<List<Section>, Boolean, Int>() {

    var actionListener: ActionListener? = null

    override fun buildModels(sections: List<Section>, showIndicators: Boolean, level: Int) {
        sections.forEachIndexed { index, section ->
            when (section) {
                is Section001 -> {
                    section001 {
                        id(section.toString())
                        section(section)
                        showIndicators(showIndicators)
                        actionListener(this@SectionsController.actionListener)
                    }
                }
                is Section002 -> {
                    section002 {
                        id(section.toString())
                        section(section)
                        showIndicators(showIndicators)
                        actionListener(this@SectionsController.actionListener)
                    }
                }
                is Section003 -> {
                    section003 {
                        id(section.toString())
                        section(section)
                        showIndicators(showIndicators)
                        actionListener(this@SectionsController.actionListener)
                    }
                }
                is Section004 -> {
                    section004 {
                        id(section.toString())
                        section(section)
                        showIndicators(showIndicators)
                        actionListener(this@SectionsController.actionListener)
                    }
                }
                is Section005 -> {
                    section005 {
                        id(section.toString())
                        section(section)
                        showIndicators(showIndicators)
                        actionListener(this@SectionsController.actionListener)
                    }
                }
                is Section006 -> {
                    section006 {
                        id(section.toString())
                        section(section)
                        showIndicators(showIndicators)
                        parentLevel(level)
                        actionListener(this@SectionsController.actionListener)
                    }
                }
                is Section007 -> {
                    section007 {
                        id(section.toString())
                        section(section)
                        showIndicators(showIndicators)
                        parentLevel(level)
                        actionListener(this@SectionsController.actionListener)
                    }
                }
                is Section008 -> {
                    section008 {
                        id(section.toString())
                        section(section)
                        showIndicators(showIndicators)
                        actionListener(this@SectionsController.actionListener)
                    }
                }
                is Section009 -> {
                    section009 {
                        id(section.toString())
                        section(section)
                        showIndicators(showIndicators)
                        actionListener(this@SectionsController.actionListener)
                    }
                }
                is Section010 -> {
                    section010 {
                        id(section.toString())
                        section(section)
                        showIndicators(showIndicators)
                        actionListener(this@SectionsController.actionListener)
                    }
                }
                is Section011 -> {
                    section011 {
                        id(section.toString())
                        section(section)
                        showIndicators(showIndicators)
                        actionListener(this@SectionsController.actionListener)
                    }
                }
                is Section012 -> {
                    section012 {
                        id(section.toString())
                        section(section)
                        showIndicators(showIndicators)
                        actionListener(this@SectionsController.actionListener)
                    }
                }
            }
        }
    }

    interface ActionListener {
        fun onActionPerformed(action: Section.Action)
    }
}

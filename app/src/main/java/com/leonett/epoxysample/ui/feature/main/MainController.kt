package com.leonett.epoxysample.ui.feature.main

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import com.leonett.epoxysample.data.GenericItem
import com.leonett.epoxysample.ui.adapter.footer
import com.leonett.epoxysample.ui.adapter.genericItem
import com.leonett.epoxysample.ui.adapter.header

class MainController : Typed2EpoxyController<String, List<GenericItem>>() {

    private var onInteractionListener: OnInteractionListener? = null

    override fun buildModels(title: String, items: List<GenericItem>) {
        header {
            id(HEADER_ID)
            title(title)
        }

        items.forEach {
            genericItem {
                id(it.id)
                item(it)
                itemClickListener { _: View? ->
                    onInteractionListener?.onItemClickListener(it)
                }
            }
        }

        footer {
            id(FOOTER_ID)
        }
    }

    fun setOnItemClickListener(onInteractionListener: OnInteractionListener) {
        this.onInteractionListener = onInteractionListener
    }

    interface OnInteractionListener {
        fun onItemClickListener(item: GenericItem)
    }

    companion object {
        private const val HEADER_ID = "HEADER_ID"
        private const val FOOTER_ID = "FOOTER_ID"
    }
}
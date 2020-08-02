package com.leonett.photofeed.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.*
import com.leonett.photofeed.R
import com.leonett.photofeed.data.mapper.Section009
import com.leonett.photofeed.ui.util.StartSnapHelper

@EpoxyModelClass(layout = R.layout.item_section_009)
abstract class Section009Model : EpoxyModelWithHolder<Section009Holder>() {

    @EpoxyAttribute
    var section: Section009? = null

    @EpoxyAttribute
    var showIndicators: Boolean = false

    @EpoxyAttribute
    var parentLevel: Int = 0

    @EpoxyAttribute
    var actionListener: SectionsController.ActionListener? = null

    override fun bind(holder: Section009Holder) {
        section?.let {
            holder.controller.actionListener = actionListener

            holder.controller.setData(it.sections, showIndicators, parentLevel + 1)
            holder.txtSectionIndicator.visibility = if (showIndicators) View.VISIBLE else View.GONE
        }
    }
}

class Section009Holder : EpoxyHolder() {

    lateinit var rvMain: EpoxyRecyclerView
    lateinit var controller: SectionsController
    lateinit var txtSectionIndicator: TextView

    override fun bindView(itemView: View) {
        rvMain = itemView.findViewById(R.id.rvMain)
        txtSectionIndicator = itemView.findViewById(R.id.txtSectionIndicator)

        controller = SectionsController()

        rvMain.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setController(controller)
        }
    }
}

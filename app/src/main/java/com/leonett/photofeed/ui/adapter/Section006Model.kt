package com.leonett.photofeed.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.*
import com.leonett.photofeed.R
import com.leonett.photofeed.data.mapper.Section006

@EpoxyModelClass(layout = R.layout.item_section_006)
abstract class Section006Model : EpoxyModelWithHolder<Section006Holder>() {

    @EpoxyAttribute
    var section: Section006? = null

    @EpoxyAttribute
    var showIndicators: Boolean = false

    @EpoxyAttribute
    var parentLevel: Int = 0

    @EpoxyAttribute
    var actionListener: SectionsController.ActionListener? = null

    override fun bind(holder: Section006Holder) {
        section?.let {
            holder.controller = SectionsController()
            holder.controller.actionListener = actionListener

            holder.rvMain.apply {
                holder.gridLayoutManager = GridLayoutManager(context, it.spanCount)
                layoutManager = holder.gridLayoutManager
                setController(holder.controller)
            }

            holder.controller.setData(it.sections, showIndicators, parentLevel + 1)
            holder.txtSectionIndicator.visibility = if (showIndicators) View.VISIBLE else View.GONE
        }
    }
}

class Section006Holder : EpoxyHolder() {

    lateinit var rvMain: EpoxyRecyclerView
    lateinit var controller: SectionsController
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var txtSectionIndicator: TextView

    override fun bindView(itemView: View) {
        rvMain = itemView.findViewById(R.id.rvMain)
        txtSectionIndicator = itemView.findViewById(R.id.txtSectionIndicator)
    }
}

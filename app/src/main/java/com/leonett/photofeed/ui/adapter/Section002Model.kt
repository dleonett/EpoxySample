package com.leonett.photofeed.ui.adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.leonett.photofeed.R
import com.leonett.photofeed.data.mapper.Section002

@EpoxyModelClass(layout = R.layout.item_section_002)
abstract class Section002Model : EpoxyModelWithHolder<Section002Holder>() {

    @EpoxyAttribute
    var section: Section002? = null

    @EpoxyAttribute
    var showIndicators: Boolean = false

    override fun bind(holder: Section002Holder) {
        section?.let {
            holder.txtTitle.text = it.title
            holder.btnAction.text = it.buttonText
            holder.txtSectionIndicator.visibility = if (showIndicators) View.VISIBLE else View.GONE
        }
    }
}

class Section002Holder : EpoxyHolder() {

    lateinit var txtTitle: TextView
    lateinit var btnAction: Button
    lateinit var txtSectionIndicator: TextView

    override fun bindView(itemView: View) {
        txtTitle = itemView.findViewById(R.id.txtTitle)
        btnAction = itemView.findViewById(R.id.btnAction)
        txtSectionIndicator = itemView.findViewById(R.id.txtSectionIndicator)
    }
}
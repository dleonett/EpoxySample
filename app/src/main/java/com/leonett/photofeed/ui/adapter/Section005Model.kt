package com.leonett.photofeed.ui.adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.leonett.photofeed.R
import com.leonett.photofeed.data.mapper.Section005

@EpoxyModelClass(layout = R.layout.item_section_005)
abstract class Section005Model : EpoxyModelWithHolder<Section005Holder>() {

    @EpoxyAttribute
    var section: Section005? = null

    @EpoxyAttribute
    var showIndicators: Boolean = false

    override fun bind(holder: Section005Holder) {
        section?.let {
            holder.btnAction.text = it.buttonText
            holder.txtSectionIndicator.visibility = if (showIndicators) View.VISIBLE else View.GONE
        }
    }
}

class Section005Holder : EpoxyHolder() {

    lateinit var btnAction: Button
    lateinit var txtSectionIndicator: TextView

    override fun bindView(itemView: View) {
        btnAction = itemView.findViewById(R.id.btnAction)
        txtSectionIndicator = itemView.findViewById(R.id.txtSectionIndicator)
    }
}

package com.leonett.photofeed.ui.adapter

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.leonett.photofeed.R
import com.leonett.photofeed.data.mapper.Section001

@EpoxyModelClass(layout = R.layout.item_section_001)
abstract class Section001Model : EpoxyModelWithHolder<Section001Holder>() {

    @EpoxyAttribute
    var section: Section001? = null

    override fun bind(holder: Section001Holder) {
        section?.let {
            holder.txtTitle.text = it.title
            holder.txtDescription.text = it.description
        }
    }
}

class Section001Holder : EpoxyHolder() {

    lateinit var txtTitle: TextView
    lateinit var txtDescription: TextView

    override fun bindView(itemView: View) {
        txtTitle = itemView.findViewById(R.id.txtTitle)
        txtDescription = itemView.findViewById(R.id.txtDescription)
    }
}
package com.leonett.photofeed.ui.adapter

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.leonett.photofeed.R

@EpoxyModelClass(layout = R.layout.item_header)
abstract class HeaderModel : EpoxyModelWithHolder<HeaderHolder>() {

    @EpoxyAttribute
    var title: String? = null
    @EpoxyAttribute
    var itemClickListener: View.OnClickListener? = null

    override fun bind(holder: HeaderHolder) {
        holder.txtTitle.text = title
    }
}

class HeaderHolder : EpoxyHolder() {

    lateinit var txtTitle: TextView

    override fun bindView(itemView: View) {
        txtTitle = itemView.findViewById(R.id.txtTitle)
    }
}
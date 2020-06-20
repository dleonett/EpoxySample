package com.leonett.photofeed.ui.adapter

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.leonett.photofeed.R

@EpoxyModelClass(layout = R.layout.item_footer)
abstract class FooterModel : EpoxyModelWithHolder<FooterHolder>() {

    @EpoxyAttribute
    var titleResId: Int? = null

    override fun bind(holder: FooterHolder) {
        titleResId?.let {
            holder.txtTitle.text = holder.txtTitle.context.getString(it)
        }
    }
}

class FooterHolder : EpoxyHolder() {

    lateinit var txtTitle: TextView

    override fun bindView(itemView: View) {
        txtTitle = itemView.findViewById(R.id.txtTitle)
    }
}
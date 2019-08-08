package com.leonett.epoxysample.ui.adapter

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.leonett.epoxysample.R

@EpoxyModelClass(layout = R.layout.item_footer)
abstract class FooterModel : EpoxyModelWithHolder<FooterHolder>() {

    override fun bind(holder: FooterHolder) {
        // no-op
    }
}

class FooterHolder : EpoxyHolder() {

    override fun bindView(itemView: View) {
        // no-op
    }
}
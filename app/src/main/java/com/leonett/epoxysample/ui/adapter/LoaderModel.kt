package com.leonett.epoxysample.ui.adapter

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.leonett.epoxysample.R

@EpoxyModelClass(layout = R.layout.item_loader)
abstract class LoaderModel : EpoxyModelWithHolder<LoaderHolder>() {

    override fun bind(holder: LoaderHolder) {
        // no-op
    }
}

class LoaderHolder : EpoxyHolder() {

    override fun bindView(itemView: View) {
        // no-op
    }
}
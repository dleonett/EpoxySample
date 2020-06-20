package com.leonett.photofeed.ui.adapter

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.leonett.photofeed.R

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
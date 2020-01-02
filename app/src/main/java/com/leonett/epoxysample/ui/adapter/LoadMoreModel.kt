package com.leonett.epoxysample.ui.adapter

import android.view.View
import android.widget.Button
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.leonett.epoxysample.R

@EpoxyModelClass(layout = R.layout.item_load_more)
abstract class LoadMoreModel : EpoxyModelWithHolder<LoadMoreHolder>() {

    @EpoxyAttribute
    var itemClickListener: View.OnClickListener? = null

    override fun bind(holder: LoadMoreHolder) {
        holder.btnLoadMore.setOnClickListener(itemClickListener)
    }
}

class LoadMoreHolder : EpoxyHolder() {

    lateinit var btnLoadMore: Button

    override fun bindView(itemView: View) {
        btnLoadMore = itemView.findViewById(R.id.btnLoadMore)
    }
}
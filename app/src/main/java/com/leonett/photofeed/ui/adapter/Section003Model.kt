package com.leonett.photofeed.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.leonett.photofeed.R
import com.leonett.photofeed.data.mapper.Section003

@EpoxyModelClass(layout = R.layout.item_section_003)
abstract class Section003Model : EpoxyModelWithHolder<Section003Holder>() {

    @EpoxyAttribute
    var section: Section003? = null

    override fun bind(holder: Section003Holder) {
        section?.let {
            holder.txtTitle.text = it.title
            holder.txtDescription.text = it.description

            Glide.with(holder.imgCover.context)
                .load(it.imageUrl)
                .apply(RequestOptions().placeholder(R.color.gray_light))
                .into(holder.imgCover)
        }
    }
}

class Section003Holder : EpoxyHolder() {

    lateinit var txtTitle: TextView
    lateinit var txtDescription: TextView
    lateinit var imgCover: ImageView

    override fun bindView(itemView: View) {
        txtTitle = itemView.findViewById(R.id.txtTitle)
        txtDescription = itemView.findViewById(R.id.txtDescription)
        imgCover = itemView.findViewById(R.id.imgCover)
    }
}
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
import com.leonett.photofeed.data.mapper.Section004

@EpoxyModelClass(layout = R.layout.item_section_004)
abstract class Section004Model : EpoxyModelWithHolder<Section004Holder>() {

    @EpoxyAttribute
    var section: Section004? = null

    @EpoxyAttribute
    var showIndicators: Boolean = false

    override fun bind(holder: Section004Holder) {
        section?.let {
            Glide.with(holder.imgCover.context)
                .load(it.imageUrl)
                .apply(RequestOptions().placeholder(R.color.gray_light))
                .into(holder.imgCover)

            holder.txtSectionIndicator.visibility = if (showIndicators) View.VISIBLE else View.GONE
        }
    }
}

class Section004Holder : EpoxyHolder() {

    lateinit var imgCover: ImageView
    lateinit var txtSectionIndicator: TextView

    override fun bindView(itemView: View) {
        imgCover = itemView.findViewById(R.id.imgCover)
        txtSectionIndicator = itemView.findViewById(R.id.txtSectionIndicator)
    }
}
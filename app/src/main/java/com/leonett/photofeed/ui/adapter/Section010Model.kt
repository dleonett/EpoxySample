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
import com.leonett.photofeed.data.mapper.Section010


@EpoxyModelClass(layout = R.layout.item_section_010)
abstract class Section010Model : EpoxyModelWithHolder<Section010Holder>() {

    @EpoxyAttribute
    var section: Section010? = null

    @EpoxyAttribute
    var showIndicators: Boolean = false

    @EpoxyAttribute
    var actionListener: SectionsController.ActionListener? = null

    override fun bind(holder: Section010Holder) {
        section?.let {
            holder.txtDescription.text = it.description
            holder.txtSectionIndicator.visibility = if (showIndicators) View.VISIBLE else View.GONE

            Glide.with(holder.imgCover.context)
                .load(it.imageUrl)
                .apply(RequestOptions().placeholder(R.color.gray_light))
                .into(holder.imgCover)
        }
    }
}

class Section010Holder : EpoxyHolder() {

    lateinit var txtDescription: TextView
    lateinit var imgCover: ImageView
    lateinit var txtSectionIndicator: TextView

    override fun bindView(itemView: View) {
        txtDescription = itemView.findViewById(R.id.txtDescription)
        imgCover = itemView.findViewById(R.id.imgCover)
        txtSectionIndicator = itemView.findViewById(R.id.txtSectionIndicator)
    }
}

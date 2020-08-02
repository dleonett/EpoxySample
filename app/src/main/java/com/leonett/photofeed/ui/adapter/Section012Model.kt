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
import com.leonett.photofeed.data.mapper.Section012

@EpoxyModelClass(layout = R.layout.item_section_012)
abstract class Section012Model : EpoxyModelWithHolder<Section012Holder>() {

    @EpoxyAttribute
    var section: Section012? = null

    @EpoxyAttribute
    var showIndicators: Boolean = false

    @EpoxyAttribute
    var actionListener: SectionsController.ActionListener? = null

    override fun bind(holder: Section012Holder) {
        section?.let {
            Glide.with(holder.imgCover.context)
                .load(it.imageUrl)
                .apply(RequestOptions().placeholder(R.color.gray_light))
                .into(holder.imgCover)

            holder.txtTitle.text = it.title
            holder.txtSectionIndicator.visibility = if (showIndicators) View.VISIBLE else View.GONE

            it.action?.let {
                holder.container.setOnClickListener { _ ->
                    actionListener?.onActionPerformed(it)
                }
            }
        }
    }

    override fun unbind(holder: Section012Holder) {
        holder.container.setOnClickListener(null)
    }
}

class Section012Holder : EpoxyHolder() {

    lateinit var imgCover: ImageView
    lateinit var txtTitle: TextView
    lateinit var txtSectionIndicator: TextView
    lateinit var container: View

    override fun bindView(itemView: View) {
        imgCover = itemView.findViewById(R.id.imgCover)
        txtTitle = itemView.findViewById(R.id.txtTitle)
        txtSectionIndicator = itemView.findViewById(R.id.txtSectionIndicator)
        container = itemView
    }
}

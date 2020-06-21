package com.leonett.photofeed.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.leonett.photofeed.R

@EpoxyModelClass(layout = R.layout.item_profile_info)
abstract class ProfileInfoModel : EpoxyModelWithHolder<ProfileInfoHolder>() {

    @EpoxyAttribute
    var iconResId: Int? = null

    @EpoxyAttribute
    var titleResId: Int? = null

    @EpoxyAttribute
    var subtitleResId: Int? = null

    override fun bind(holder: ProfileInfoHolder) {
        iconResId?.let {
            holder.imgIcon.setImageResource(it)
            holder.imgIcon.visibility = View.VISIBLE
        } ?: run {
            holder.imgIcon.visibility = View.GONE
        }

        titleResId?.let {
            holder.txtTitle.text = holder.txtTitle.context.getString(it)
            holder.txtTitle.visibility = View.VISIBLE
        } ?: run {
            holder.txtTitle.visibility = View.GONE
        }

        subtitleResId?.let {
            holder.txtSubtitle.text = holder.txtSubtitle.context.getString(it)
            holder.txtSubtitle.visibility = View.VISIBLE
        } ?: run {
            holder.txtSubtitle.visibility = View.GONE
        }
    }
}

class ProfileInfoHolder : EpoxyHolder() {

    lateinit var imgIcon: ImageView
    lateinit var txtTitle: TextView
    lateinit var txtSubtitle: TextView

    override fun bindView(itemView: View) {
        imgIcon = itemView.findViewById(R.id.imgIcon)
        txtTitle = itemView.findViewById(R.id.txtTitle)
        txtSubtitle = itemView.findViewById(R.id.txtSubtitle)
    }
}
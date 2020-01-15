package com.leonett.epoxysample.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.model.Story

@EpoxyModelClass(layout = R.layout.item_story)
abstract class StoryModel : EpoxyModelWithHolder<StoryHolder>() {

    @EpoxyAttribute
    var story: Story? = null
    @EpoxyAttribute
    var itemClickListener: View.OnClickListener? = null

    override fun bind(holder: StoryHolder) {
        Glide.with(holder.imgPicture.context)
                .load(story?.imgUrl)
                .apply(RequestOptions().circleCrop())
                .apply(RequestOptions().placeholder(R.drawable.placeholder_image_circle))
                .into(holder.imgPicture)

        holder.txtUsername.text = story?.username
        holder.container.setOnClickListener(itemClickListener)
    }
}

class StoryHolder : EpoxyHolder() {

    lateinit var imgPicture: ImageView
    lateinit var txtUsername: TextView
    lateinit var container: View

    override fun bindView(itemView: View) {
        imgPicture = itemView.findViewById(R.id.imgPicture)
        txtUsername = itemView.findViewById(R.id.txtUsername)
        container = itemView
    }
}
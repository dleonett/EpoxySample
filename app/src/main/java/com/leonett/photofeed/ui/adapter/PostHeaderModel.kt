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
import com.leonett.photofeed.data.model.Post

@EpoxyModelClass(layout = R.layout.item_post_header)
abstract class PostHeaderModel : EpoxyModelWithHolder<PostHeaderHolder>() {

    @EpoxyAttribute
    var post: Post? = null

    @EpoxyAttribute
    var itemClickListener: View.OnClickListener? = null

    override fun bind(holder: PostHeaderHolder) {
        Glide.with(holder.imgAvatar.context)
            .load(post?.avatarUrl)
            .apply(RequestOptions().circleCrop())
            .apply(RequestOptions().placeholder(R.drawable.placeholder_image_circle))
            .into(holder.imgAvatar)

        holder.txtUsername.text = post?.username
        holder.txtLocation.text = post?.location
        holder.txtLocation.visibility =
            if (post?.location.isNullOrEmpty()) View.GONE else View.VISIBLE

        holder.imgAvatar.setOnClickListener(itemClickListener)
        holder.txtUsername.setOnClickListener(itemClickListener)
    }
}

class PostHeaderHolder : EpoxyHolder() {

    lateinit var imgAvatar: ImageView
    lateinit var txtUsername: TextView
    lateinit var txtLocation: TextView

    override fun bindView(itemView: View) {
        imgAvatar = itemView.findViewById(R.id.imgAvatar)
        txtUsername = itemView.findViewById(R.id.txtUsername)
        txtLocation = itemView.findViewById(R.id.txtLocation)
    }
}
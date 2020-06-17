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
import com.leonett.epoxysample.data.model.User

@EpoxyModelClass(layout = R.layout.item_profile_header)
abstract class ProfileHeaderModel : EpoxyModelWithHolder<ProfileHeaderHolder>() {

    @EpoxyAttribute
    var user: User? = null

    @EpoxyAttribute
    var itemClickListener: View.OnClickListener? = null

    override fun bind(holder: ProfileHeaderHolder) {
        Glide.with(holder.imgAvatar.context)
            .load(user?.avatarUrl)
            .apply(RequestOptions().circleCrop())
            .apply(RequestOptions().placeholder(R.drawable.placeholder_image_circle))
            .into(holder.imgAvatar)

        holder.txtPosts.text = user?.posts.toString()
        holder.txtFollowers.text = user?.followers.toString()
        holder.txtFollowing.text = user?.following.toString()
        holder.txtTitle.text = user?.displayName
        holder.txtDescription.text = user?.description
        holder.container.setOnClickListener(itemClickListener)
    }
}

class ProfileHeaderHolder : EpoxyHolder() {

    lateinit var imgAvatar: ImageView
    lateinit var txtPosts: TextView
    lateinit var txtFollowers: TextView
    lateinit var txtFollowing: TextView
    lateinit var txtTitle: TextView
    lateinit var txtDescription: TextView
    lateinit var container: View

    override fun bindView(itemView: View) {
        imgAvatar = itemView.findViewById(R.id.imgAvatar)
        txtPosts = itemView.findViewById(R.id.txtPosts)
        txtFollowers = itemView.findViewById(R.id.txtFollowers)
        txtFollowing = itemView.findViewById(R.id.txtFollowing)
        txtTitle = itemView.findViewById(R.id.txtTitle)
        txtDescription = itemView.findViewById(R.id.txtDescription)
        container = itemView
    }
}
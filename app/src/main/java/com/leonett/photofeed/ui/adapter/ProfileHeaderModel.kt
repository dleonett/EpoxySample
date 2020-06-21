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
import com.leonett.photofeed.data.model.User
import org.apache.commons.lang3.StringEscapeUtils

@EpoxyModelClass(layout = R.layout.item_profile_header)
abstract class ProfileHeaderModel : EpoxyModelWithHolder<ProfileHeaderHolder>() {

    @EpoxyAttribute
    var user: User? = null

    @EpoxyAttribute
    var onInteractionListener: OnInteractionListener? = null

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
        holder.txtDescription.text = StringEscapeUtils.unescapeJava(user?.description)
        holder.txtExternalLink.text = user?.externalUrl
        holder.txtExternalLink.visibility = when {
            user?.externalUrl.isNullOrEmpty() -> View.GONE
            else -> View.VISIBLE
        }
        holder.txtExternalLink.setOnClickListener {
            onInteractionListener?.onExternalLinkClick(user?.externalUrl!!)
        }
    }

    interface OnInteractionListener {
        fun onExternalLinkClick(externalUrl: String)
    }
}

class ProfileHeaderHolder : EpoxyHolder() {

    lateinit var imgAvatar: ImageView
    lateinit var txtPosts: TextView
    lateinit var txtFollowers: TextView
    lateinit var txtFollowing: TextView
    lateinit var txtTitle: TextView
    lateinit var txtDescription: TextView
    lateinit var txtExternalLink: TextView
    lateinit var container: View

    override fun bindView(itemView: View) {
        imgAvatar = itemView.findViewById(R.id.imgAvatar)
        txtPosts = itemView.findViewById(R.id.txtPosts)
        txtFollowers = itemView.findViewById(R.id.txtFollowers)
        txtFollowing = itemView.findViewById(R.id.txtFollowing)
        txtTitle = itemView.findViewById(R.id.txtTitle)
        txtDescription = itemView.findViewById(R.id.txtDescription)
        txtExternalLink = itemView.findViewById(R.id.txtExternalLink)
        container = itemView
    }
}
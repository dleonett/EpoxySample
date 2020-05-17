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
import com.leonett.epoxysample.data.model.Post
import com.leonett.epoxysample.util.setRoundCorners

@EpoxyModelClass(layout = R.layout.item_post)
abstract class PostModel : EpoxyModelWithHolder<PostHolder>() {

    @EpoxyAttribute
    var post: Post? = null
    @EpoxyAttribute
    var itemClickListener: View.OnClickListener? = null

    override fun bind(holder: PostHolder) {
        Glide.with(holder.imgPicture.context)
            .load(post?.imgUrl)
            .apply(RequestOptions().placeholder(R.color.gray_light))
            .into(holder.imgPicture)

        holder.txtTitle.text = post?.title
        holder.txtSubtitle.text = post?.subtitle
        holder.container.setOnClickListener(itemClickListener)
        holder.imgPicture.setRoundCorners(R.dimen.spacing_xxxs)
    }
}

class PostHolder : EpoxyHolder() {

    lateinit var imgPicture: ImageView
    lateinit var txtTitle: TextView
    lateinit var txtSubtitle: TextView
    lateinit var container: View

    override fun bindView(itemView: View) {
        imgPicture = itemView.findViewById(R.id.imgPicture)
        txtTitle = itemView.findViewById(R.id.txtTitle)
        txtSubtitle = itemView.findViewById(R.id.txtSubtitle)
        container = itemView
    }
}
package com.leonett.epoxysample.ui.adapter

import android.view.View
import android.widget.ImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.model.Post

@EpoxyModelClass(layout = R.layout.item_posts_grid_item)
abstract class PostsGridItemModel : EpoxyModelWithHolder<PostsGridItemHolder>() {

    @EpoxyAttribute
    var post: Post? = null
    @EpoxyAttribute
    var itemClickListener: View.OnClickListener? = null

    override fun bind(holder: PostsGridItemHolder) {
        Glide.with(holder.imgPicture.context)
            .load(post?.imgUrl)
            .apply(RequestOptions().placeholder(R.color.gray_light))
            .into(holder.imgPicture)

        holder.container.setOnClickListener(itemClickListener)
    }
}

class PostsGridItemHolder : EpoxyHolder() {

    lateinit var imgPicture: ImageView
    lateinit var container: View

    override fun bindView(itemView: View) {
        imgPicture = itemView.findViewById(R.id.imgPicture)
        container = itemView
    }
}
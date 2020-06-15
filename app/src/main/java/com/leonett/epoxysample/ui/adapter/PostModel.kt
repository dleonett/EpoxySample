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

@EpoxyModelClass(layout = R.layout.item_post)
abstract class PostModel : EpoxyModelWithHolder<PostHolder>() {

    @EpoxyAttribute
    var post: Post? = null

    @EpoxyAttribute
    var onInteractionListener: OnInteractionListener? = null

    override fun bind(holder: PostHolder) {
        post?.let {
            Glide.with(holder.imgPicture.context)
                .load(it.imgUrl)
                .apply(RequestOptions().placeholder(R.color.gray_light))
                .into(holder.imgPicture)

            holder.txtLikes.text = holder.txtLikes.context.getString(R.string.post_likes, "100")
            holder.txtTitle.text = it.title
            holder.txtSubtitle.text = it.subtitle
            holder.btnLike.setOnClickListener { _ ->
                onInteractionListener?.onPostLikeClick(it)
            }
            holder.btnComment.setOnClickListener { _ ->
                onInteractionListener?.onPostCommentClick(it)
            }
            holder.btnShare.setOnClickListener { _ ->
                onInteractionListener?.onPostShareClick(it)
            }
        }
    }

    interface OnInteractionListener {
        fun onPostLikeClick(post: Post)
        fun onPostCommentClick(post: Post)
        fun onPostShareClick(post: Post)
    }
}

class PostHolder : EpoxyHolder() {

    lateinit var imgPicture: ImageView
    lateinit var txtLikes: TextView
    lateinit var txtTitle: TextView
    lateinit var txtSubtitle: TextView
    lateinit var btnLike: ImageView
    lateinit var btnComment: ImageView
    lateinit var btnShare: ImageView

    override fun bindView(itemView: View) {
        imgPicture = itemView.findViewById(R.id.imgPicture)
        txtLikes = itemView.findViewById(R.id.txtLikes)
        txtTitle = itemView.findViewById(R.id.txtTitle)
        txtSubtitle = itemView.findViewById(R.id.txtSubtitle)
        btnLike = itemView.findViewById(R.id.btnLike)
        btnComment = itemView.findViewById(R.id.btnComment)
        btnShare = itemView.findViewById(R.id.btnShare)
    }
}
package com.leonett.photofeed.ui.adapter

import android.graphics.drawable.AnimatedVectorDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.leonett.photofeed.R
import com.leonett.photofeed.data.model.Post
import com.leonett.photofeed.ui.util.DoubleClickListener

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

            holder.txtLikes.text =
                holder.txtLikes.context.getString(R.string.post_likes, it.likes.toString())
            holder.txtTitle.text = it.title
            holder.txtTitle.visibility = if (it.title.isNullOrEmpty()) View.GONE else View.VISIBLE
            holder.txtSubtitle.text =
                holder.txtSubtitle.context.getString(R.string.post_comments, it.comments.toString())
            holder.btnLike.setImageResource(
                if (it.likedByMe)
                    R.drawable.ic_like_on
                else
                    R.drawable.ic_like_off
            )

            holder.imgPicture.setOnClickListener(object : DoubleClickListener() {
                override fun onDoubleClick() {
                    val drawable = holder.imgHeart.drawable

                    if (drawable is AnimatedVectorDrawable) {
                        drawable.start()
                    } else if (drawable is AnimatedVectorDrawableCompat) {
                        drawable.start()
                    }

                    onInteractionListener?.onPostLikeDoubleClick(it)
                }
            })
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
        fun onPostLikeDoubleClick(post: Post)
        fun onPostLikeClick(post: Post)
        fun onPostCommentClick(post: Post)
        fun onPostShareClick(post: Post)
    }
}

class PostHolder : EpoxyHolder() {

    lateinit var imgPicture: ImageView
    lateinit var imgHeart: ImageView
    lateinit var txtLikes: TextView
    lateinit var txtTitle: TextView
    lateinit var txtSubtitle: TextView
    lateinit var btnLike: ImageView
    lateinit var btnComment: ImageView
    lateinit var btnShare: ImageView

    override fun bindView(itemView: View) {
        imgPicture = itemView.findViewById(R.id.imgPicture)
        imgHeart = itemView.findViewById(R.id.imgHeart)
        txtLikes = itemView.findViewById(R.id.txtLikes)
        txtTitle = itemView.findViewById(R.id.txtTitle)
        txtSubtitle = itemView.findViewById(R.id.txtSubtitle)
        btnLike = itemView.findViewById(R.id.btnLike)
        btnComment = itemView.findViewById(R.id.btnComment)
        btnShare = itemView.findViewById(R.id.btnShare)
    }
}
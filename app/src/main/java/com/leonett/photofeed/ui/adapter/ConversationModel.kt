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
import com.leonett.photofeed.data.model.Conversation
import com.leonett.photofeed.util.toDate
import com.leonett.photofeed.util.toShortFormat

@EpoxyModelClass(layout = R.layout.item_conversation)
abstract class ConversationModel : EpoxyModelWithHolder<ConversationHolder>() {

    @EpoxyAttribute
    var conversation: Conversation? = null

    @EpoxyAttribute
    var itemClickListener: View.OnClickListener? = null

    override fun bind(holder: ConversationHolder) {
        conversation?.let {
            Glide.with(holder.imgAvatar.context)
                .load(it.user.avatarUrl)
                .apply(RequestOptions().circleCrop())
                .apply(RequestOptions().placeholder(R.drawable.placeholder_image_circle))
                .into(holder.imgAvatar)

            holder.txtTitle.text = it.user.displayName
            holder.txtSubtitle.text = it.lastMessage?.body
            holder.txtDate.text = it.lastMessage?.timestamp?.toDate()?.toShortFormat()
            holder.container.setOnClickListener(itemClickListener)
        }
    }
}

class ConversationHolder : EpoxyHolder() {

    lateinit var container: View
    lateinit var imgAvatar: ImageView
    lateinit var txtTitle: TextView
    lateinit var txtSubtitle: TextView
    lateinit var txtDate: TextView

    override fun bindView(itemView: View) {
        container = itemView
        imgAvatar = itemView.findViewById(R.id.imgAvatar)
        txtTitle = itemView.findViewById(R.id.txtTitle)
        txtSubtitle = itemView.findViewById(R.id.txtSubtitle)
        txtDate = itemView.findViewById(R.id.txtDate)
    }
}

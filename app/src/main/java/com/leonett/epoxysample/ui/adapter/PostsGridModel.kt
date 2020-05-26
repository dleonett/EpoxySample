package com.leonett.epoxysample.ui.adapter

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.*
import com.leonett.epoxysample.R
import com.leonett.epoxysample.data.model.Post

@EpoxyModelClass(layout = R.layout.item_posts_grid)
abstract class PostsGridModel : EpoxyModelWithHolder<PostsGridHolder>(),
    PostsGridController.OnInteractionListener {

    @EpoxyAttribute
    var posts: List<Post>? = null
    @EpoxyAttribute
    var onInteractionListener: OnInteractionListener? = null

    override fun bind(holder: PostsGridHolder) {
        holder.postsGridController.setOnInteractionListener(this)
        holder.postsGridController.setData(posts)
    }

    override fun onPostClick(post: Post) {
        onInteractionListener?.onPostClick(post)
    }

    interface OnInteractionListener {
        fun onPostClick(post: Post)
    }
}

class PostsGridHolder : EpoxyHolder() {

    lateinit var rvPostsGrid: EpoxyRecyclerView
    lateinit var container: View
    lateinit var postsGridController: PostsGridController

    override fun bindView(itemView: View) {
        rvPostsGrid = itemView.findViewById(R.id.rvPostsGrid)
        container = itemView

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        postsGridController =
            PostsGridController()

        rvPostsGrid.apply {
            layoutManager = GridLayoutManager(context, 3)
            //layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setController(postsGridController)
            addItemDecoration(EpoxyItemSpacingDecorator(resources.getDimension(R.dimen.spacing_xxxxs).toInt()))
        }
    }
}
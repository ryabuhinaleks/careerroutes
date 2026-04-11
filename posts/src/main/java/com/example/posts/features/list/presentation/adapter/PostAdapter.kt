package com.example.posts.features.list.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.features.list.domain.model.Post
import com.example.uicomponents.R
import com.example.uicomponents.old.postCard.PostCardView

class PostAdapter(
    private val listener: Listener,
) : ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postCardView = PostCardView(parent.context)
        return PostViewHolder(postCardView, listener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PostViewHolder(
        private val postCardView: PostCardView,
        private val listener: Listener,
    ) : RecyclerView.ViewHolder(postCardView) {

        fun bind(post: Post) = with(postCardView) {
            setContent(
                title = post.title,
                description = post.description
            )
            setFavoriteIcon(
                res = when (post.isFavorite) {
                    true -> R.drawable.ic_favorite
                    false -> R.drawable.ic_favorite_border
                },
                listener = { listener.onFavorite(post) }
            )
            postCardView.setOnClickListener {
                listener.onDetailPostClick(post.id)
            }
        }
    }

    interface Listener {
        fun onDetailPostClick(postId: Int)
        fun onFavorite(post: Post)
    }
}

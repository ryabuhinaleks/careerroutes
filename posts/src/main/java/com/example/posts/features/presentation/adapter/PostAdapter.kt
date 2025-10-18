package com.example.posts.features.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.databinding.ItemPostBinding
import com.example.posts.features.domain.model.Post
import com.example.uicomponents.R

class PostAdapter(
    private val listener: Listener,
) : ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PostViewHolder(
        private val binding: ItemPostBinding,
        private val listener: Listener,
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(post: Post) = with(binding) {
            postTitle.text = post.title
            postDescription.text = post.description
            postFavorite.apply {
                when (post.isFavorite) {
                    true -> R.drawable.ic_favorite
                    false -> R.drawable.ic_favorite_border
                }.run(::setImageResource)
                setOnClickListener {
                    listener.onFavorite(post)
                }
            }

            binding.root.setOnClickListener { listener.onDetailPostClick(post.id) }
        }
    }

    interface Listener {
        fun onDetailPostClick(postId: Int)
        fun onFavorite(post: Post)
    }
}

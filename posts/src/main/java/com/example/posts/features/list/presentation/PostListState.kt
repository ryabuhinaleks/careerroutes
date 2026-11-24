package com.example.posts.features.list.presentation

import com.example.posts.features.list.domain.model.Post

sealed class PostListState {
    data object Loading : PostListState()
    data object Error : PostListState()
    data class Content(val posts: List<Post>) : PostListState()
}

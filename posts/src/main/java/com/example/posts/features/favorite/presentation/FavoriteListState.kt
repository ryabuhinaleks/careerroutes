package com.example.posts.features.favorite.presentation

import com.example.posts.features.list.domain.model.Post

sealed class FavoriteListState {
    data object Loading : FavoriteListState()
    data object Error : FavoriteListState()
    data class Content(val posts: List<Post>) : FavoriteListState()
}

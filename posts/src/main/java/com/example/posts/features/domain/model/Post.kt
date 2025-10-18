package com.example.posts.features.domain.model

data class Post(
    val id: Int,
    val title: String,
    val description: String,
    val isFavorite: Boolean = false
)

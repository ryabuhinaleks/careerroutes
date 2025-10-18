package com.example.posts.features.domain

import com.example.posts.features.domain.model.Post

interface PostInteractor {
    suspend fun getPosts(userId: Int): List<Post>
    suspend fun getFavorite(userId: Int): List<Post>
    suspend fun addFavorite(post: Post, userId: Int)
    suspend fun deleteFavorite(post: Post, userId: Int)
}

package com.example.posts.features.list.domain

import com.example.posts.features.list.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostInteractor {
    fun getPosts(userId: Int): Flow<List<Post>>
    fun getFavorite(userId: Int): Flow<List<Post>>
    suspend fun addFavorite(post: Post, userId: Int)
    suspend fun deleteFavorite(post: Post, userId: Int)
}

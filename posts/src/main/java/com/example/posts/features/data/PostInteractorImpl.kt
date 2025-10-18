package com.example.posts.features.data

import com.example.database.dao.PostDao
import com.example.posts.features.data.api.PostApiService
import com.example.posts.features.data.mapper.PostMapper
import com.example.posts.features.domain.PostInteractor
import com.example.posts.features.domain.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostInteractorImpl(
    private val postApi: PostApiService,
    private val postDao: PostDao,
    private val mapper: PostMapper
) : PostInteractor {

    override suspend fun getPosts(userId: Int): List<Post> {
        val posts = postApi.getPosts(userId)
        return withContext(Dispatchers.IO) {
            posts.map { mapper.toDomain(it) }
        }
    }

    override suspend fun getFavorite(userId: Int): List<Post> {
        val posts = postDao.getPostsByUserId(userId)
        return withContext(Dispatchers.IO) {
            posts.map { mapper.toDomain(it) }
        }
    }

    override suspend fun addFavorite(post: Post, userId: Int) {
        postDao.insertPost(mapper.toEntity(post, userId))
    }

    override suspend fun deleteFavorite(post: Post, userId: Int) {
        postDao.deletePost(mapper.toEntity(post, userId))
    }
}

package com.example.posts.features.list.data

import com.example.database.dao.PostDao
import com.example.posts.features.list.data.api.PostApiService
import com.example.posts.features.list.data.mapper.PostMapper
import com.example.posts.features.list.domain.PostInteractor
import com.example.posts.features.list.domain.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class PostInteractorImpl(
    private val postApi: PostApiService,
    private val postDao: PostDao,
    private val mapper: PostMapper,
) : PostInteractor {

    override fun getPosts(userId: Int): Flow<List<Post>> {
        return flow { emit(postApi.getPosts(userId)) }
            .map { posts -> posts.map { mapper.toDomain(it) } }
            .flowOn(Dispatchers.Default)
    }

    override fun getFavorite(userId: Int): Flow<List<Post>> {
        return postDao.getPostsByUserIdFlow(userId)
            .map { posts -> posts.map { mapper.toDomain(it) } }
            .flowOn(Dispatchers.Default)
    }

    override suspend fun addFavorite(post: Post, userId: Int) {
        postDao.insertPost(mapper.toEntity(post, userId))
    }

    override suspend fun deleteFavorite(post: Post, userId: Int) {
        postDao.deletePost(mapper.toEntity(post, userId))
    }
}

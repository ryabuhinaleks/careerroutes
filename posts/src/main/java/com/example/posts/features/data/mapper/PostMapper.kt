package com.example.posts.features.data.mapper

import com.example.database.entity.PostEntity
import com.example.posts.features.data.model.PostResponse
import com.example.posts.features.domain.model.Post

class PostMapper {

    fun toDomain(postResponse: PostResponse): Post {
        return Post(
            id = postResponse.id,
            title = postResponse.title,
            description = postResponse.body
        )
    }

    fun toDomain(postEntity: PostEntity): Post {
        return Post(
            id = postEntity.id,
            title = postEntity.title,
            description = postEntity.body
        )
    }

    fun toEntity(post: Post, userId: Int): PostEntity {
        return PostEntity(
            id = post.id,
            userId = userId,
            title = post.title,
            body = post.description
        )
    }
}

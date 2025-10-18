package com.example.posts.features.data.api

import com.example.posts.features.data.model.PostResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApiService {
    @GET("posts")
    suspend fun getPosts(@Query("userId") userId: Int): List<PostResponse>
}

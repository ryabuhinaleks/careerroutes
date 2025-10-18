package com.example.users.features.data.api

import com.example.users.features.data.model.UserResponse
import retrofit2.http.GET

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}

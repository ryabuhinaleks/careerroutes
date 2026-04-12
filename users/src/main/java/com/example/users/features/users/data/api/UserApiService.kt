package com.example.users.features.users.data.api

import com.example.users.features.users.data.model.UserResponse
import retrofit2.http.GET

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}

package com.example.users.features.domain

import com.example.users.features.domain.model.User

interface UserInteractor {
    suspend fun getUsers(): List<User>
}

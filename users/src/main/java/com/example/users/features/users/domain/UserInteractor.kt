package com.example.users.features.users.domain

import com.example.users.features.users.domain.model.User

interface UserInteractor {
    suspend fun getUsers(): List<User>
}

package com.example.users.features.users.data

import com.example.users.features.users.data.api.UserApiService
import com.example.users.features.users.data.mapper.UserMapper
import com.example.users.features.users.domain.UserInteractor
import com.example.users.features.users.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserInteractorImpl(
    private val userApi: UserApiService,
    private val mapper: UserMapper
) : UserInteractor {

    override suspend fun getUsers(): List<User> {
        val users = userApi.getUsers()
        return withContext(Dispatchers.IO) {
            users.map { mapper.toDomain(it) }
        }
    }
}

package com.example.users.features.users.data.mapper

import com.example.users.features.users.data.model.UserResponse
import com.example.users.features.users.domain.model.User

class UserMapper {

    fun toDomain(userResponse: UserResponse): User {
        return User(
            id = userResponse.id,
            name = userResponse.name,
            email = userResponse.email,
            phone = userResponse.phone
        )
    }
}

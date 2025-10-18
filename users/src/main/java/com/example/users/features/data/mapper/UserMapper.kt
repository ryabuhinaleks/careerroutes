package com.example.users.features.data.mapper

import com.example.users.features.data.model.UserResponse
import com.example.users.features.domain.model.User

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

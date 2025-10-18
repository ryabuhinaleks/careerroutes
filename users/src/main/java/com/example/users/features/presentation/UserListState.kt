package com.example.users.features.presentation

import com.example.users.features.domain.model.User

sealed class UserListState {
    data object Loading : UserListState()
    data object Error : UserListState()
    data class Content(val users: List<User>) : UserListState()
}

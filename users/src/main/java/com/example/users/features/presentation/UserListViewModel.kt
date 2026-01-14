package com.example.users.features.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.users.features.domain.UserInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserListViewModel(
    private val userInteractor: UserInteractor
) : ViewModel() {

    private val _state = MutableStateFlow<UserListState>(UserListState.Loading)
    val state: StateFlow<UserListState> = _state.asStateFlow()

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                _state.value = UserListState.Loading
                val users = userInteractor.getUsers()
                _state.value = UserListState.Content(users)
            } catch (ex: Exception) {
                _state.value = UserListState.Error
            }
        }
    }
}

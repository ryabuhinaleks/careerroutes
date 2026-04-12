package com.example.users.features.users.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.users.features.users.domain.UserInteractor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserListViewModel(
    private val userInteractor: UserInteractor,
) : ViewModel() {

    private val _state = MutableStateFlow<UserListState>(UserListState.Loading)
    val state: StateFlow<UserListState> = _state.asStateFlow()

    fun load() {
        viewModelScope.launch {
            try {
                _state.value = UserListState.Loading
                delay(2000)
                val users = userInteractor.getUsers()
                _state.value = UserListState.Content(users)
            } catch (ex: Exception) {
                _state.value = UserListState.Error
            }
        }
    }
}

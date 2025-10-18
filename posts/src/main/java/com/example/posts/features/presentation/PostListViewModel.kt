package com.example.posts.features.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posts.features.domain.PostInteractor
import com.example.posts.features.domain.model.Post
import com.example.posts.features.presentation.EventState.Notification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostListViewModel(
    private val userId: Int,
    private val postInteractor: PostInteractor
) : ViewModel() {

    private val _state = MutableStateFlow<PostListState>(PostListState.Loading)
    val state: StateFlow<PostListState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<Notification>()
    val event: SharedFlow<Notification> = _event.asSharedFlow()

    init {
        load()
    }

    fun addFavorite(post: Post) {
        viewModelScope.launch {
            postInteractor.addFavorite(post, userId)
            _event.emit(Notification("Add"))
        }
    }

    fun deleteFavorite(post: Post) {
        viewModelScope.launch {
            postInteractor.deleteFavorite(post, userId)
            _event.emit(Notification("Delete"))
        }
    }

    // Переделать на flow
    private fun load() {
        viewModelScope.launch {
            _state.value = PostListState.Loading
            val (posts, favorites) = awaitAll(
                async { postInteractor.getPosts(userId) },
                async { postInteractor.getFavorite(userId) }
            )

            val updatedPosts = withContext(Dispatchers.IO) {
                posts.map { post ->
                    post.copy(isFavorite = favorites.any { favorite -> favorite.id == post.id })
                }
            }

            _state.value = PostListState.Content(updatedPosts)
        }
    }

}
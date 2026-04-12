package com.example.posts.features.list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posts.features.list.domain.PostInteractor
import com.example.posts.features.list.domain.model.Post
import com.example.posts.features.list.presentation.EventState.Notification
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PostListViewModel(
    private val userId: Int,
    private val postInteractor: PostInteractor,
) : ViewModel() {

    private val _state = MutableStateFlow<PostListState>(PostListState.Loading)
    val state: StateFlow<PostListState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<Notification>()
    val event: SharedFlow<Notification> = _event.asSharedFlow()

    fun addFavorite(post: Post) {
        viewModelScope.launch {
            postInteractor.addFavorite(post, userId)
            _event.emit(Notification(true))
        }
    }

    fun deleteFavorite(post: Post) {
        viewModelScope.launch {
            postInteractor.deleteFavorite(post, userId)
            _event.emit(Notification(false))
        }
    }

    fun load() {
        viewModelScope.launch {
            _state.value = PostListState.Loading

            postInteractor.getPosts(userId)
                .combine(postInteractor.getFavorite(userId)) { posts, favorite ->
                    posts to favorite
                }
                .map { (posts, favorites) ->
                    posts.map { post ->
                        post.copy(isFavorite = favorites.any { favorite -> favorite.id == post.id })
                    }
                }
                .catch { e ->
                    _state.value = PostListState.Error
                }
                .collect {
                    _state.value = PostListState.Content(it)
                }
        }
    }

}
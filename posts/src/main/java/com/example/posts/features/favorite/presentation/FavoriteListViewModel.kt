package com.example.posts.features.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posts.features.favorite.presentation.EventState.Notification
import com.example.posts.features.list.domain.PostInteractor
import com.example.posts.features.list.domain.model.Post
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class FavoriteListViewModel(
    private val postInteractor: PostInteractor,
) : ViewModel() {

    private val _state = MutableStateFlow<FavoriteListState>(FavoriteListState.Loading)
    val state: StateFlow<FavoriteListState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<Notification>()
    val event: SharedFlow<Notification> = _event.asSharedFlow()

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    fun deleteFavorite(post: Post) {
        viewModelScope.launch {
            postInteractor.deleteFavoriteByPostId(post.id)
            _event.emit(Notification(false))
        }
    }

    fun onChangeQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun load() {
        viewModelScope.launch {
            _state.value = FavoriteListState.Loading
            combine(
                postInteractor.getFavorites(),
                _query
            ) { posts, query -> filterPosts(posts, query) }
                .catch { e ->
                    _state.value = FavoriteListState.Error
                }
                .collect { filteredPosts ->
                    _state.value = FavoriteListState.Content(filteredPosts)
                }
        }
    }

    private fun filterPosts(posts: List<Post>, query: String): List<Post> {
        if (query.isEmpty()) return posts
        return posts.filter { post -> post.title.contains(query, ignoreCase = true) }
    }
}
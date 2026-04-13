package com.example.posts.features.favorite.presentation

sealed class EventState {
    data class Notification(val isAddFavorite: Boolean) : EventState()
}
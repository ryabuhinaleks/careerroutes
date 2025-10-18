package com.example.posts.features.list.presentation

sealed class EventState {
    data class Notification(val message: String) : EventState()
}
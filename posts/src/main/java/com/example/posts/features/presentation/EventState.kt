package com.example.posts.features.presentation

sealed class EventState {
    data class Notification(val message: String) : EventState()
}
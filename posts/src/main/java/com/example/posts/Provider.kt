package com.example.posts

import com.example.navigation.FragmentScreen

object PostNavigationProvider {
    fun providePostListScreen(userId: Int): FragmentScreen = PostListScreen(userId)
}

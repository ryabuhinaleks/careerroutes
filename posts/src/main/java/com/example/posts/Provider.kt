package com.example.posts

import com.example.navigation.FragmentScreen

object PostNavigationProvider {
    fun providePostListScreen(userId: Int): FragmentScreen = PostListOldScreen(userId)
    fun providePostListComposeScreen(userId: Int): FragmentScreen = PostListComposeScreen(userId)
}

package com.example.careerroutes.navigation

import com.example.navigation.AppScreens
import com.example.navigation.FragmentScreen
import com.example.posts.PostNavigationProvider
import com.example.users.UsersNavigationProvider

class AppScreensImpl : AppScreens {
    override fun getUserListScreen(): FragmentScreen =
        UsersNavigationProvider.provideUserListScreen()

    override fun getPostListByUserScreen(userId: Int): FragmentScreen =
        PostNavigationProvider.providePostListScreen(userId)

    override fun getSettingsScreen(): FragmentScreen =
        FeatureSettingsScreen()
}
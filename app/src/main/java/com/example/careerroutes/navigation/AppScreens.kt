package com.example.careerroutes.navigation

import com.example.navigation.AppScreens
import com.example.navigation.FragmentScreen
import com.example.posts.PostNavigationProvider
import com.example.users.UsersNavigationProvider
import org.koin.core.component.KoinComponent

class AppScreensImpl(
    private val isCompose: Boolean,
) : AppScreens, KoinComponent {
    override fun getUserListScreen(): FragmentScreen {
        return when (isCompose) {
            true -> UsersNavigationProvider.provideUserListComposeScreen()
            else -> UsersNavigationProvider.provideUserListScreen()
        }
    }

    override fun getPostListByUserScreen(userId: Int): FragmentScreen =
        PostNavigationProvider.providePostListScreen(userId)

    override fun getSettingsScreen(): FragmentScreen =
        FeatureSettingsScreen()
}
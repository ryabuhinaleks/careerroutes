package com.example.users

import com.example.navigation.FragmentScreen

object UsersNavigationProvider {
    fun provideUserListScreen(): FragmentScreen = UserListScreen()
    fun provideUserListComposeScreen(): FragmentScreen = UserListComposeScreen()
}

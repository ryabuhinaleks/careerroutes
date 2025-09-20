package com.example.navigation

interface HasNavigator {
    fun provideNavigator(): FragmentNavigator
}
package com.example.navigation

sealed class FragmentCommand {
    data class Forward(val screen: FragmentScreen) : FragmentCommand()
    data class Replace(val screen: FragmentScreen) : FragmentCommand()
    data object Back : FragmentCommand()
}
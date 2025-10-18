package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

interface FragmentScreen {
    val tag: String
    val arguments: Bundle?
    fun createFragment(): Fragment
}

interface DialogScreen {
    val tag: String
    val arguments: Bundle?
    fun createFragment(): DialogFragment
}

interface AppScreens {
    fun getUserListScreen(): FragmentScreen
    fun getPostListByUserScreen(userId: Int): FragmentScreen
    fun getMainScreen(): FragmentScreen
}
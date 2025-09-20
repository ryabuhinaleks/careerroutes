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
   // fun getFeature1MainScreen(): FragmentScreen
   // fun getFeature1DetailScreen(itemId: Long): FragmentScreen
    fun getMainScreen(): FragmentScreen
}
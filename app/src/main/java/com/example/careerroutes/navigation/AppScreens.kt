package com.example.careerroutes.navigation

import com.example.navigation.AppScreens
import com.example.navigation.FragmentScreen

class AppScreensImpl : AppScreens {

    override fun getMainScreen(): FragmentScreen =
        Feature1DetailScreen()
}
package com.example.careerroutes.di

import androidx.fragment.app.FragmentActivity
import com.example.careerroutes.R
import com.example.careerroutes.navigation.AppScreensImpl
import com.example.navigation.AppScreens
import com.example.navigation.FragmentNavigator
import org.koin.dsl.module

val appModule = module {
    single<AppScreens> { AppScreensImpl() }

    single { (activity: FragmentActivity) ->
        FragmentNavigator(
            fragmentManager = activity.supportFragmentManager,
            containerId = R.id.fragment_container
        )
    }
}
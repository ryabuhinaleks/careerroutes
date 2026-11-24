package com.example.careerroutes.di

import androidx.fragment.app.FragmentActivity
import com.example.careerroutes.R
import com.example.careerroutes.navigation.AppScreensImpl
import com.example.navigation.AppScreens
import com.example.navigation.FragmentNavigator
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single<Boolean>(named("isCompose")) { true }

    single<AppScreens> {
        AppScreensImpl(
            isCompose = get(named("isCompose"))
        )
    }

    single { (activity: FragmentActivity) ->
        FragmentNavigator(
            fragmentManager = activity.supportFragmentManager,
            containerId = R.id.fragment_container
        )
    }
}
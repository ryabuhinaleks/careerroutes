package com.example.careerroutes.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.careerroutes.R
import com.example.navigation.AppScreens
import com.example.navigation.FragmentCommand
import com.example.navigation.FragmentNavigator
import com.example.navigation.HasNavigator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class RootActivity : AppCompatActivity(), HasNavigator {

    private val navigator: FragmentNavigator by inject { parametersOf(this) }
    private val appScreens: AppScreens by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigator.execute(
                FragmentCommand.Replace(appScreens.getUserListScreen())
            )
        }
    }

    override fun provideNavigator(): FragmentNavigator = navigator
}
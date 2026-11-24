package com.example.careerroutes.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.careerroutes.features.settings.SettingsFragment
import com.example.navigation.FragmentScreen

internal class FeatureSettingsScreen : FragmentScreen {
    override val tag: String = "SettingsFragment"
    override val arguments: Bundle? = null
    override fun createFragment(): Fragment = SettingsFragment()
}
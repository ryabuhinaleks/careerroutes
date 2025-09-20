package com.example.careerroutes.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.careerroutes.features.test.FirstFragment
import com.example.navigation.FragmentScreen

internal class Feature1DetailScreen : FragmentScreen {
    override val tag: String = "FirstFragment"
    override val arguments: Bundle? = null
    override fun createFragment(): Fragment = FirstFragment()
}
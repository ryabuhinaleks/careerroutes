package com.example.careerroutes.root

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.careerroutes.R
import com.example.careerroutes.features.settings.LocaleManager
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

        val currentLanguage = LocaleManager.getCurrentLanguage(this)
        LocaleManager.setLocale(this, currentLanguage)

        showInitialFragment()
    }


    override fun provideNavigator(): FragmentNavigator = navigator

    fun restartActivity() {
        val intent = Intent(this, RootActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }

    private fun showInitialFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            navigator.execute(
                FragmentCommand.Replace(appScreens.getUserListScreen())
            )
        }
    }
}
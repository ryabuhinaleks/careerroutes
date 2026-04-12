package com.example.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.navigation.FragmentScreen
import com.example.users.features.users.presentation.UserListScreen
import com.example.users.features.users.presentation.old.UserListFragment

internal class UserListScreen : FragmentScreen {
    override val tag: String = "UserListFragment"
    override val arguments: Bundle? = null
    override fun createFragment(): Fragment = UserListFragment()
}

internal class UserListComposeScreen : FragmentScreen {
    override val tag: String = "UserListComposeFragment"
    override val arguments: Bundle? = null
    override fun createFragment(): Fragment = UserListScreen()
}

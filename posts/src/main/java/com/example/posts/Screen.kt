package com.example.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.navigation.FragmentScreen
import com.example.posts.features.presentation.PostListFragment
import com.example.posts.features.presentation.PostListFragment.Companion.USER_ID

internal class PostListScreen(val userId: Int) : FragmentScreen {
    override val tag: String = "PostListFragment"
    override val arguments: Bundle? = Bundle().apply { putInt(USER_ID, userId) }
    override fun createFragment(): Fragment = PostListFragment()
}

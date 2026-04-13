package com.example.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.navigation.FragmentScreen
import com.example.posts.features.favorite.presentation.FavoriteListScreen
import com.example.posts.features.list.presentation.PostListScreen
import com.example.posts.features.list.presentation.old.PostListFragment
import com.example.posts.features.list.presentation.old.PostListFragment.Companion.USER_ID

internal class PostListOldScreen(val userId: Int) : FragmentScreen {
    override val tag: String = "PostListFragment"
    override val arguments: Bundle? = Bundle().apply { putInt(USER_ID, userId) }
    override fun createFragment(): Fragment = PostListFragment()
}

internal class PostListComposeScreen(val userId: Int) : FragmentScreen {
    override val tag: String = "PostListComposeScreen"
    override val arguments: Bundle? = Bundle().apply { putInt(USER_ID, userId) }
    override fun createFragment(): Fragment = PostListScreen()
}

internal class FavoriteListComposeScreen() : FragmentScreen {
    override val tag: String = "PostListComposeScreen"
    override val arguments: Bundle? = null
    override fun createFragment(): Fragment = FavoriteListScreen()
}

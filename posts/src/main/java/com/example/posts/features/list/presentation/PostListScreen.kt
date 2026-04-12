package com.example.posts.features.list.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import coil.size.Dimension
import com.example.core.BaseFragment
import com.example.core.R.string
import com.example.posts.features.info.PostInfoBottomSheet
import com.example.posts.features.list.domain.model.Post
import com.example.uicomponents.compose.card.postCard.PostCard
import com.example.uicomponents.compose.loader.Loader
import com.example.uicomponents.compose.topbar.TopBar
import com.example.uicomponents.compose.topbar.TopBarIcon
import com.example.uicomponents.compose.utils.Dimens
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PostListScreen : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setContent {
            val viewModel by viewModel<PostListViewModel> {
                val userId = arguments?.getInt(USER_ID) ?: 0
                parametersOf(userId)
            }
            val state by viewModel.state.collectAsState()
            PostListScreen(
                state = state,
                addFavorite = { viewModel.addFavorite(it) },
                deleteFavorite = { viewModel.deleteFavorite(it) }
            )

            LaunchedEffect(Unit) {
                viewModel.load()
            }
        }
    }

    @SuppressLint("NotConstructor")
    @Composable
    fun PostListScreen(
        state: PostListState,
        addFavorite: (Post) -> Unit,
        deleteFavorite: (Post) -> Unit,
    ) {
        when (state) {
            is PostListState.Content -> Content(state.posts, addFavorite, deleteFavorite)

            PostListState.Error -> {
                // Без обработки
            }

            PostListState.Loading -> Loading()
        }
    }

    @Composable
    private fun Loading() {
        Loader()
    }

    @Composable
    private fun Content(
        posts: List<Post>,
        addFavorite: (Post) -> Unit,
        deleteFavorite: (Post) -> Unit,
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    title = stringResource(string.posts),
                    topBarIcon = TopBarIcon.NONE
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues),
                contentPadding = PaddingValues(vertical = Dimens.spaceDefault)
            ) {
                items(
                    items = posts,
                    key = { it.id }
                ) { post ->
                    PostCard(
                        title = post.title,
                        description = post.description,
                        isFavorite = post.isFavorite,
                        onFavoriteClick = {
                            if (post.isFavorite) {
                                deleteFavorite(post)
                            } else {
                                addFavorite(post)
                            }
                        },
                        onDetailPostClick = { onDetailPostClick(post.id) },
                        onDetailPostLongClick = { onDetailPostLongClick(post) }
                    )
                }
            }
        }
    }

    private fun onDetailPostClick(postId: Int) {
        Log.e("aaaa", "postId = " + postId)
    }

    private fun onDetailPostLongClick(post: Post) {
        PostInfoBottomSheet.Companion.newInstance().apply { setInfo(post) }
            .show(parentFragmentManager, PostInfoBottomSheet.Companion.TAG)
    }

    companion object {
        const val USER_ID = "USER_ID"
    }
}
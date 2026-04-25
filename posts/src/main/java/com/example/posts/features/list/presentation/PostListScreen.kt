package com.example.posts.features.list.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.BaseFragment
import com.example.core.R
import com.example.navigation.FragmentCommand
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
            val state by viewModel.state.collectAsStateWithLifecycle()
            val snackbarHostState = remember { SnackbarHostState() }

            PostListContent(
                state = state,
                snackbarHostState = snackbarHostState,
                addFavorite = viewModel::addFavorite,
                deleteFavorite = viewModel::deleteFavorite,
            )

            LaunchedEffect(Unit) {
                viewModel.load()

                viewModel.event.collect { notification ->
                    when (notification) {
                        is EventState.Notification -> {
                            val message = when (notification.isAddFavorite) {
                                true -> R.string.added_to_favorites
                                else -> R.string.removed_from_favorites
                            }.run(::getString)
                            snackbarHostState.showSnackbar(message, duration = SnackbarDuration.Short)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun PostListContent(
        state: PostListState,
        snackbarHostState: SnackbarHostState,
        addFavorite: (Post) -> Unit,
        deleteFavorite: (Post) -> Unit,
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding(),
            topBar = {
                TopBar(
                    title = stringResource(R.string.posts),
                    topBarIcon = TopBarIcon.FAVORITE
                ) {
                    openFavorite()
                }
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }
        ) { paddingValues ->
            when (state) {
                is PostListState.Content -> Content(
                    paddingValues,
                    state.posts,
                    addFavorite,
                    deleteFavorite
                )

                PostListState.Error -> {
                    // Без обработки
                }

                PostListState.Loading -> Loading()
            }
        }
    }

    @Composable
    private fun Loading() {
        Loader()
    }

    @Composable
    private fun Content(
        paddingValues: PaddingValues,
        posts: List<Post>,
        addFavorite: (Post) -> Unit,
        deleteFavorite: (Post) -> Unit,
    ) {
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
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

    private fun onDetailPostClick(postId: Int) {
        Log.e("aaaa", "postId = " + postId)
    }

    private fun onDetailPostLongClick(post: Post) {
        PostInfoBottomSheet.Companion.newInstance().apply { setInfo(post) }
            .show(parentFragmentManager, PostInfoBottomSheet.Companion.TAG)
    }

    private fun openFavorite() {
        navigator.execute(
            FragmentCommand.Forward(appScreens.getFavoriteListComposeScreen())
        )
    }

    companion object {
        const val USER_ID = "USER_ID"
    }
}
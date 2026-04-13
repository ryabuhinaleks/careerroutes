package com.example.posts.features.favorite.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.BaseFragment
import com.example.core.R
import com.example.posts.features.list.domain.model.Post
import com.example.uicomponents.compose.card.postCard.PostCard
import com.example.uicomponents.compose.loader.Loader
import com.example.uicomponents.compose.textfield.SearchTextField
import com.example.uicomponents.compose.topbar.TopBar
import com.example.uicomponents.compose.topbar.TopBarIcon
import com.example.uicomponents.compose.utils.Dimens
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteListScreen : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }

            val viewModel by viewModel<FavoriteListViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val query by viewModel.query.collectAsStateWithLifecycle()

            FavoriteListContent(
                query = query,
                state = state,
                snackbarHostState = snackbarHostState,
                deleteFavorite = { viewModel.deleteFavorite(it) },
                onChangeQuery = { viewModel.onChangeQuery(it) }
            )

            LaunchedEffect(Unit) {
                viewModel.load()

                viewModel.event
                    .collect {
                        snackbarHostState.showSnackbar(getString(R.string.removed_from_favorites))
                    }
            }
        }
    }

    @Composable
    private fun FavoriteListContent(
        query: String,
        state: FavoriteListState,
        snackbarHostState: SnackbarHostState,
        deleteFavorite: (Post) -> Unit,
        onChangeQuery: (String) -> Unit,
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding(),
            topBar = {
                Column {
                    TopBar(
                        title = stringResource(R.string.favorite),
                        topBarIcon = TopBarIcon.NONE,
                        visibleDivider = false
                    )
                    SearchTextField(
                        value = query,
                        onChangeQuery = { onChangeQuery(it) },
                        onClose = { onChangeQuery("") }
                    )
                }

            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }
        ) { paddingValues ->
            when (state) {
                is FavoriteListState.Content -> Content(
                    paddingValues,
                    state.posts,
                    deleteFavorite
                )

                FavoriteListState.Error -> {
                    // Без обработки
                }

                FavoriteListState.Loading -> Loading()
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
        deleteFavorite: (Post) -> Unit,
    ) {
        val focusManager = LocalFocusManager.current
        val scrollState = rememberLazyListState()

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues),
            state = scrollState,
            contentPadding = PaddingValues(vertical = Dimens.spaceDefault)
        ) {
            items(
                items = posts,
                key = { it.id }
            ) { post ->
                PostCard(
                    modifier = Modifier.animateItem(),
                    title = post.title,
                    description = post.description,
                    isFavorite = post.isFavorite,
                    onFavoriteClick = { deleteFavorite(post) },
                    onDetailPostClick = { },
                    onDetailPostLongClick = { }
                )
            }
        }

        LaunchedEffect(scrollState.isScrollInProgress) {
            if (scrollState.isScrollInProgress) {
                focusManager.clearFocus()
            }
        }
    }
}
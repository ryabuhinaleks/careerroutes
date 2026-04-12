package com.example.users.features.users.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.core.BaseFragment
import com.example.core.R.string
import com.example.navigation.FragmentCommand
import com.example.uicomponents.compose.card.userCard.UserCard
import com.example.uicomponents.compose.loader.Loader
import com.example.uicomponents.compose.topbar.TopBar
import com.example.uicomponents.compose.topbar.TopBarIcon
import com.example.uicomponents.compose.utils.Dimens
import com.example.users.features.info.InfoBottomSheet
import com.example.users.features.users.domain.model.User
import com.example.users.features.users.presentation.UserListState.Content
import com.example.users.features.users.presentation.UserListState.Error
import com.example.users.features.users.presentation.UserListState.Loading
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListScreen : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setContent {
            val viewModel by viewModel<UserListViewModel>()
            val state by viewModel.state.collectAsState()
            UserListScreen(state)

            LaunchedEffect(Unit) {
                viewModel.load()
            }
        }
    }

    @SuppressLint("NotConstructor")
    @Composable
    fun UserListScreen(state: UserListState) {
        Scaffold(
            topBar = {
                TopBar(
                    title = stringResource(string.user_list),
                    topBarIcon = TopBarIcon.FILTER
                ) { onFilterClick() }
            }
        ) { paddingValues ->
            when (state) {
                is Loading -> {
                    Loading()
                }

                is Error -> {
                    // Нет обработки
                }

                is Content -> {
                    Content(paddingValues, state.users)
                }
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
        users: List<User>,
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(Dimens.spaceDefault),
            verticalArrangement = Arrangement.spacedBy(Dimens.spaceDefault)
        ) {
            items(
                items = users,
                key = { user -> user.id }
            ) { user ->
                UserCard(
                    userName = user.name,
                    userEmail = user.email,
                    userPhone = user.phone,
                    onClick = { onUserClick(user) },
                    onLongClick = { onUserLongClick(user) }
                )
            }
        }
    }

    private fun onUserClick(user: User) {
        navigator.execute(
            FragmentCommand.Forward(appScreens.getPostListByUserScreen(user.id))
        )
    }

    private fun onUserLongClick(user: User) {
        InfoBottomSheet.newInstance().apply { setInfo(user) }
            .show(parentFragmentManager, InfoBottomSheet.TAG)
    }

    private fun onFilterClick() {
        navigator.execute(
            FragmentCommand.Forward(appScreens.getSettingsScreen())
        )
    }
}

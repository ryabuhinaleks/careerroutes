package com.example.users.features.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.BaseFragment
import com.example.core.R.string.user_list
import com.example.navigation.FragmentCommand
import com.example.uicomponents.R
import com.example.uicomponents.old.decorator.addSpacingDecorationIfNeeded
import com.example.uicomponents.old.topbar.TopBarIcon
import com.example.users.databinding.FragmentUserListBinding
import com.example.users.features.domain.model.User
import com.example.users.features.presentation.adapter.UserAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : BaseFragment(), UserAdapter.Listener {

    private val viewModel: UserListViewModel by viewModel()
    private val binding by lazy { FragmentUserListBinding.inflate(layoutInflater) }
    private val userAdapter by lazy { UserAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeUsers()
        topBar.setRightIcon(TopBarIcon.FILTER) {
            navigator.execute(
                FragmentCommand.Forward(appScreens.getSettingsScreen())
            )
        }
    }

    override fun onResume() {
        super.onResume()
        binding.topBar.setTitle(getString(user_list))
    }

    override fun onUserClick(user: User) {
        navigator.execute(
            FragmentCommand.Forward(appScreens.getPostListByUserScreen(user.id))
        )
    }

    private fun setupRecyclerView() = with(binding.list) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = userAdapter
        addSpacingDecorationIfNeeded(R.dimen.spacing_16, R.dimen.spacing_16)
    }

    private fun observeUsers() = with(binding) {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is UserListState.Loading -> {
                        progress.show()
                    }

                    is UserListState.Error -> {
                        Log.e("aaaa", "error")
                    }

                    is UserListState.Content -> {
                        progress.hide()
                        userAdapter.submitList(state.users)
                    }
                }
            }
        }
    }
}

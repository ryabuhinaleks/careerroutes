package com.example.posts.features.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.BaseFragment
import com.example.posts.databinding.FragmentPostListBinding
import com.example.posts.features.domain.model.Post
import com.example.posts.features.presentation.adapter.PostAdapter
import com.example.uicomponents.R
import com.example.uicomponents.decorator.addSpacingDecorationIfNeeded
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PostListFragment : BaseFragment(), PostAdapter.Listener {

    private val viewModel: PostListViewModel by viewModel {
        val userId = arguments?.getInt(USER_ID) ?: 0
        parametersOf(userId)
    }
    private val binding by lazy { FragmentPostListBinding.inflate(layoutInflater) }
    private val userAdapter by lazy { PostAdapter(this) }

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

    }

    override fun onDetailPostClick(postId: Int) {
        Log.e("aaaa", "postId = " + postId)
    }

    override fun onFavorite(post: Post) {
        when (post.isFavorite) {
            true -> viewModel.deleteFavorite(post)
            else -> viewModel.addFavorite(post)
        }
    }

    private fun setupRecyclerView() = with(binding.list) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = userAdapter
        addSpacingDecorationIfNeeded(verticalMarginRes = R.dimen.spacing_16)
    }

    private fun observeUsers() = with(binding) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is PostListState.Loading -> {
                            progress.show()
                        }
                        is PostListState.Error -> {
                            progress.hide()
                            Log.e("PostList", "Error")
                        }
                        is PostListState.Content -> {
                            progress.hide()
                            userAdapter.submitList(state.posts)
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        is EventState.Notification -> {
                            Toast.makeText(requireContext(), event.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val USER_ID = "USER_ID"
    }
}
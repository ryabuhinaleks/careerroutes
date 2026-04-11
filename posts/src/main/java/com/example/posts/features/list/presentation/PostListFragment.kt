package com.example.posts.features.list.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.BaseFragment
import com.example.core.R as Res
import com.example.posts.databinding.FragmentPostListBinding
import com.example.posts.features.list.domain.model.Post
import com.example.posts.features.list.presentation.adapter.PostAdapter
import com.example.uicomponents.R
import com.example.uicomponents.old.decorator.addEdgePaddingDecoration
import com.google.android.material.snackbar.Snackbar
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
        viewModel.load()
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
        addEdgePaddingDecoration(
            topPaddingRes = R.dimen.spacing_16,
            bottomPaddingRes = R.dimen.spacing_16
        )
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
                            val message = when (event.isAddFavorite) {
                                true -> Res.string.added_to_favorites
                                else -> Res.string.removed_from_favorites
                            }.run(::getString)
                            Snackbar.make(
                                binding.root,
                                message,
                                Snackbar.LENGTH_SHORT
                            ).show()
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
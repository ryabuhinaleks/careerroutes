package com.example.posts.features.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.posts.R
import com.example.posts.databinding.BottomSheetPostBinding
import com.example.posts.features.list.domain.model.Post
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PostInfoBottomSheet : BottomSheetDialogFragment() {

    private val binding by lazy { BottomSheetPostBinding.inflate(layoutInflater) }
    private lateinit var post: Post

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        title.text = post.title
        description.text = post.description
        root.setBackgroundResource(R.drawable.bottom_sheet_rounded)
    }

    fun setInfo(post: Post) {
        this.post = post
    }

    companion object {
        const val TAG = "PostInfoBottomSheet"

        fun newInstance(): PostInfoBottomSheet {
            return PostInfoBottomSheet()
        }
    }
}
package com.example.users.features.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.users.databinding.BottomSheetCustomBinding
import com.example.users.features.users.domain.model.User
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InfoBottomSheet : BottomSheetDialogFragment() {

    private val binding by lazy { BottomSheetCustomBinding.inflate(layoutInflater) }
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        name.text = user.name
        email.text = user.email
        phone.text = user.phone

        val bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    fun setInfo(user: User) {
        this.user = user
    }

    companion object {
        const val TAG = "InfoBottomSheet"

        fun newInstance(): InfoBottomSheet {
            return InfoBottomSheet()
        }
    }
}
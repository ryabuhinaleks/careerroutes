package com.example.users.features.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import com.example.uicomponents.compose.utils.Dimens
import com.example.users.features.users.domain.model.User
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InfoBottomSheetScreen : BottomSheetDialogFragment() {

    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setContent {
            InfoContent()
        }
    }

    @Composable
    private fun InfoContent() {
        Column(
            modifier = Modifier.padding(Dimens.spaceDefault),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .width(Dimens.bsWidth)
                    .height(Dimens.bsHeight)
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(Dimens.cornerSmall)
                    )
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.spaceDefault),
                fontSize = Dimens.textTitle,
                text = user.name
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.spaceMedium),
                fontSize = Dimens.textBody,
                text = user.email
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.spaceMedium),
                fontSize = Dimens.textBody,
                text = user.phone
            )
        }
    }

    fun setInfo(user: User) {
        this.user = user
    }

    companion object {
        const val TAG = "InfoBottomSheet"

        fun newInstance(): InfoBottomSheetScreen {
            return InfoBottomSheetScreen()
        }
    }
}
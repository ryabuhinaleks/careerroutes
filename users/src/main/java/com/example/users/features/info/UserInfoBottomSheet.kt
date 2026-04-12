package com.example.users.features.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.uicomponents.compose.utils.Dimens
import com.example.users.features.users.domain.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoBottomSheet(
    user: User?,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()

    if (user != null) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            containerColor = Color.White,
            sheetState = sheetState
        ) {
            UserInfoContent(user = user)
        }
    }
}

@Composable
private fun UserInfoContent(user: User) {
    Column(
        modifier = Modifier.padding(horizontal = Dimens.spaceDefault)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
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

@Preview
@Composable
private fun PreviewUserInfoBottomSheet() {
    UserInfoContent(
        user = User(
            id = 1,
            name = "Иван Иванов",
            email = "ivan@example.com",
            phone = "+7 (999) 123-45-67"
        )
    )
}
package com.example.uicomponents.compose.card.userCard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.uicomponents.compose.utils.Dimens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    userName: String,
    userEmail: String,
    userPhone: String,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        shape = RoundedCornerShape(Dimens.cornerDefault),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.elevationSmall
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.spaceDefault)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = userName,
                fontSize = Dimens.textTitle,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.spaceSmall),
                fontSize = Dimens.textBody,
                text = userEmail
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.spaceSmall),
                fontSize = Dimens.textBody,
                text = userPhone
            )
        }

    }
}
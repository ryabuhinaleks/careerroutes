package com.example.uicomponents.compose.card.postCard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.uicomponents.R
import com.example.uicomponents.compose.utils.Dimens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onDetailPostClick: () -> Unit,
    onDetailPostLongClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .combinedClickable(
                onClick = onDetailPostClick,
                onLongClick = onDetailPostLongClick
            ),
    ) {
        Row(
            modifier = Modifier.padding(Dimens.spaceDefault)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    fontSize = Dimens.textTitle,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(Dimens.spaceMedium))
                Text(
                    text = description,
                    maxLines = 1,
                    fontSize = Dimens.textBody,
                    color = colorResource(R.color.gray),
                    overflow = TextOverflow.Ellipsis
                )
            }
            IconButton(onClick = onFavoriteClick, modifier = Modifier.size(Dimens.spaceLarge)) {
                Icon(
                    painter = painterResource(
                        if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
                    ),
                    contentDescription = "favorite",
                    tint = colorResource(R.color.red)
                )
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = colorResource(R.color.divider_color)
        )
    }
}

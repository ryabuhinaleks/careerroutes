package com.example.uicomponents.compose.topbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uicomponents.R
import com.example.uicomponents.compose.utils.Dimens

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    topBarIcon: TopBarIcon,
    visibleDivider: Boolean = true,
    onMenuClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.topbar)
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Dimens.spaceDefault)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    fontSize = 18.sp
                )
                topBarIcon.res?.let {
                    IconButton(
                        modifier = Modifier.size(Dimens.sizeMedium),
                        onClick = onMenuClick
                    ) {
                        Icon(
                            painter = painterResource(it),
                            contentDescription = "filter"
                        )
                    }
                }
            }
        }
        if (visibleDivider) {
            HorizontalDivider(
                thickness = 1.dp,
                color = colorResource(R.color.divider_color)
            )
        }
    }
}

enum class TopBarIcon(@DrawableRes val res: Int? = null) {
    NONE(),
    FILTER(R.drawable.ic_filter_list),
    FAVORITE(R.drawable.ic_favorites)
}

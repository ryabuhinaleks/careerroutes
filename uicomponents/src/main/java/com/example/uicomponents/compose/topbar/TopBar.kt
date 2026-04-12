package com.example.uicomponents.compose.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.uicomponents.R

@Composable
fun TopBar(
    modifier: Modifier,
    title: String,
    topBarIcon: TopBarIcon,
    onFilterClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    fontSize = TextUnit(value = 18f, type = TextUnitType.Sp)
                )
                if (topBarIcon == TopBarIcon.FILTER) {
                    IconButton(
                        modifier = Modifier
                            .size(24.dp),
                        onClick = onFilterClick
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_filter_list),
                            contentDescription = "filter"
                        )
                    }
                }
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = colorResource(R.color.divider_color)
        )
    }
}

enum class TopBarIcon {
    NONE,
    FILTER
}

@Preview
@Composable
fun test() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        TopBar(modifier = Modifier, title = "Заголовок", topBarIcon = TopBarIcon.NONE, {})
        Spacer(modifier = Modifier.height(16.dp))
        TopBar(modifier = Modifier, title = "Заголовок", topBarIcon = TopBarIcon.FILTER, {})
    }
}
package com.example.uicomponents.compose.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.uicomponents.R
import com.example.uicomponents.compose.utils.Dimens

@Composable
fun ButtonStyle(
    modifier: Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF424242),
            contentColor = Color.White
        )
    ) {
        content()
    }
}

@Preview
@Composable
fun test() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ButtonStyle(Modifier.fillMaxWidth(), {}) {
            Text(text = "Проверка")
        }
        Spacer(Modifier.height(Dimens.spaceDefault))
        ButtonStyle(Modifier.fillMaxWidth(), {}) {
            Row {
                Icon(painter = painterResource(R.drawable.ic_filter_list), null)
                Text(text = "Фильтр")
            }
        }
    }
}

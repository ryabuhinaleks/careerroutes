package com.example.uicomponents.compose.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uicomponents.R
import com.example.uicomponents.compose.utils.Dimens

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    onChangeQuery: (String) -> Unit = {},
    onClose: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.spaceDefault),
            value = value,
            onValueChange = onChangeQuery,
            placeholder = { Text("Введите текст") },
            singleLine = true,
            trailingIcon = {
                if (value.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.clickable { onClose() },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear"
                    )
                }
            }
        )
        HorizontalDivider(
            modifier = Modifier.padding(top = Dimens.spaceMedium),
            thickness = 1.dp,
            color = colorResource(R.color.divider_color)
        )
    }
}


@Preview
@Composable
fun test() {
    SearchTextField(value = "")
}
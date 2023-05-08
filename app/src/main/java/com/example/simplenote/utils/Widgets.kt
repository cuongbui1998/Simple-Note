package com.example.simplenote.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonItem(
    modifier: Modifier = Modifier,
    text: String = "",
    selected: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            modifier = Modifier
                .size(24.dp),
            selected = selected,
            onClick = onClick
        )
        Text(
            modifier = Modifier
                .padding(start = 12.dp)
                .fillMaxWidth(),
            text = text
        )
    }
}
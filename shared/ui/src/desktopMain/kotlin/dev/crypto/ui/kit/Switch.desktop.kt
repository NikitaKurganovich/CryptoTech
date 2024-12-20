package dev.crypto.ui.kit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.crypto.ui.theme.CipherTheme

@Composable
actual fun CipherSwitch(
    modifier: Modifier,
    firstOptionText: String,
    secondOptionText: String,
    isFirst: Boolean,
    onClick: (Boolean) -> Unit
) {
    Column(
        modifier = modifier
            .wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RadioButton(
                selected = isFirst,
                onClick = { onClick(true) }
            )
            Text(
                text = firstOptionText,
                style = CipherTheme.typography.default,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RadioButton(
                selected = !isFirst,
                onClick = { onClick(false) }
            )
            Text(
                text = secondOptionText,
                style = CipherTheme.typography.default,
            )
        }
    }
}
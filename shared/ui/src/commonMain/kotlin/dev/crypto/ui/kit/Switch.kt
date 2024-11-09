package dev.crypto.ui.kit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun CipherSwitch(
    modifier: Modifier = Modifier,
    firstOptionText: String,
    secondOptionText: String,
    isFirst: Boolean = true,
    onClick: (Boolean) -> Unit
)
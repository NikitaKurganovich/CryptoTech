package dev.crypto.ui.kit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun CipherButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
)
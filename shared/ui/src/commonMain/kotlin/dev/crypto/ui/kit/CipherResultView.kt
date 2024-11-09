package dev.crypto.ui.kit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun CipherResultView(
    modifier: Modifier = Modifier,
    infoText: String,
    resultText: String,
    isError: Boolean
)
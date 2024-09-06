package ui.config

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun CipherTheme(
    content: @Composable () -> Unit,
) {
    val cipherDimensions = cipherDimensions
    CompositionLocalProvider(
        LocalCipherDimensions provides cipherDimensions,
        content = content
    )
}

object CipherTheme {
    val dimensions: CipherDimensions
        @Composable
        get() = LocalCipherDimensions.current
}
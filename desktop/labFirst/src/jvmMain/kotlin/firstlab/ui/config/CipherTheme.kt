package firstlab.ui.config

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun CipherTheme(
    content: @Composable () -> Unit,
) {
    val cipherDimensions = cipherDimensions
    val cipherTypography = cipherTypography
    CompositionLocalProvider(
        LocalCipherDimensions provides cipherDimensions,
        LocalCipherTypography provides cipherTypography,
        content = content
    )
}

object CipherTheme {
    val dimensions: CipherDimensions
        @Composable
        get() = LocalCipherDimensions.current

    val typography: CipherTypography
        @Composable
        get() = LocalCipherTypography.current
}
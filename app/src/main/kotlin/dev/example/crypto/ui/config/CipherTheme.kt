package dev.example.crypto.ui.config

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun CipherTheme(
    content: @Composable () -> Unit,
) {
    val cipherDimensions = cipherDimensions
    val cipherTypography = cipherTypography
    val cipherColors = cipherColors
    val cipherViewDimensions = StandardViewDimen
    CompositionLocalProvider(
        LocalCipherDimensions provides cipherDimensions,
        LocalCipherTypography provides cipherTypography,
        LocalCipherColors provides cipherColors,
        LocalCipherViewDimensions provides cipherViewDimensions,
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

    val colors: CipherColors
        @Composable
        get() = LocalCipherColors.current

    val viewDimensions: CipherViewDimensions
        @Composable
        get() = StandardViewDimen
}
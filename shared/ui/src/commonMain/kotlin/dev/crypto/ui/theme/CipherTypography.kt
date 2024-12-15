package dev.crypto.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily

@Composable
expect fun getPlatformFontFamily(): FontFamily

val cipherFontFamily: FontFamily
    @Composable get() = getPlatformFontFamily()

@Immutable
data class CipherTypography(
    val default: TextStyle,
    val bold: TextStyle
)

expect val cipherTypography: CipherTypography

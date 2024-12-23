package dev.crypto.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class CipherColors(
    val border: Color,
    val borderCorner: Color,
    val transparentBackground: Color,
    val text: Color,
    val hint: Color,
    val iconTint: Color,
    val resultsBackground: Color,
    val buttonFilled: Color,
    val buttonTransparent: Color,
    val fieldBackground: Color,
    val textError: Color,
    val popupBackground: Color,
    val dropdownBackground: Color,
    val background: Color,
    val topRow: Color,
)

expect val cipherColors: CipherColors

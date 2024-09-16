package dev.example.crypto.ui.config

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
    val popupBackground: Color
)

val cipherColors = CipherColors(
    border = Color(0x3623E34C),
    borderCorner = Color(0xC723E34C),
    transparentBackground = Color(0x3B000000),
    text = Color(0xFFFFFFFF),
    hint = Color(0x44FFFFFF),
    iconTint = Color(0xFF23E34C),
    resultsBackground = Color(0x1523E34C),
    buttonFilled = Color(0xFF23E34C),
    buttonTransparent = Color(0x1523E34C),
    fieldBackground = Color(0x1523E34C),
    textError = Color(0xFFFF0000),
    popupBackground = Color(0x3B23E34C)
)
package dev.crypto.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp

@Composable
expect fun getPlatformFontFamily(): FontFamily

val cipherFontFamily: FontFamily
    @Composable get() = getPlatformFontFamily()

@Immutable
data class CipherTypography(
    val default: TextStyle,
    val bold: TextStyle
)

val cipherTypography = CipherTypography(
    default = TextStyle(
        fontSize = 18.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Companion.W500,
        letterSpacing = TextUnit(value = 0.01f, type = TextUnitType.Companion.Em),
    ),
    bold = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Companion.Bold,
    )
)

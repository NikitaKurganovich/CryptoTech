package dev.crypto.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp

actual val cipherTypography: CipherTypography = CipherTypography(
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
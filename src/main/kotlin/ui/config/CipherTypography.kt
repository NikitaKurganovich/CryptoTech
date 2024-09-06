package ui.config

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Immutable
data class CipherTypography(
    val default: TextStyle,
    val bold: TextStyle
)

val cipherTypography = CipherTypography(
    default = TextStyle(
        fontSize = 16.sp
    ),
    bold = TextStyle(
        fontSize = 16.sp,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
    )
)
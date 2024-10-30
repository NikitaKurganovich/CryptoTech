package dev.crypto.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import crypto_decoding.shared.ui.generated.resources.Poppins_LightItalic
import crypto_decoding.shared.ui.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
actual fun getPlatformFontFamily() = FontFamily(
    Font(Res.font.Poppins_LightItalic)
)


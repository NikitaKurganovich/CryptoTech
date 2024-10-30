package dev.crypto.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import dev.crypto.shared.ui.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Sarpanch")

val fontFamily = FontFamily(
    Font(fontProvider = provider, googleFont = fontName)
)

@Composable
actual fun getPlatformFontFamily() = fontFamily


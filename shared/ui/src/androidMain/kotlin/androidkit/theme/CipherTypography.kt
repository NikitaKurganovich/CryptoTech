package androidkit.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
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

@Immutable
data class CipherTypography(
    val default: TextStyle,
    val bold: TextStyle
)

val cipherTypography = CipherTypography(
    default = TextStyle(
        fontSize = 18.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = TextUnit(value = 0.01f, type = TextUnitType.Em),
        fontFamily = fontFamily
    ),
    bold = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = fontFamily
    )
)

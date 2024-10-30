package dev.crypto.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf

val LocalCipherDimensions = staticCompositionLocalOf<CipherDimensions> {
    error("No CipherDimensions provided")
}

val LocalCipherTypography = staticCompositionLocalOf<CipherTypography> {
    error("No CipherTypography provided")
}

val LocalCipherColors = staticCompositionLocalOf<CipherColors> {
    error("No CipherColors provided")
}

val LocalCipherViewDimensions = staticCompositionLocalOf<CipherViewDimensions> {
    error("No CipherTheme.viewDimensions provided")
}
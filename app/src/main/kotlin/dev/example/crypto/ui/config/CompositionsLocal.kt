package dev.example.crypto.ui.config

import androidx.compose.runtime.staticCompositionLocalOf

val LocalCipherDimensions = staticCompositionLocalOf<CipherDimensions> {
    error("No CipherDimensions provided")
}

val LocalCipherTypography = staticCompositionLocalOf<CipherTypography> {
    error("No CipherTypography provided")
}
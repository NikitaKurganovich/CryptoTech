package ui.config

import androidx.compose.runtime.staticCompositionLocalOf

val LocalCipherDimensions = staticCompositionLocalOf<CipherDimensions> {
    error("No CipherDimensions provided")
}
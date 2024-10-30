package dev.crypto.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class CipherDimensions(
    val smallMinus: Dp,
    val smallDefault: Dp,
    val smallPlus: Dp,

    val mediumMinus: Dp,
    val mediumDefault: Dp,
    val mediumPlus: Dp,

    val largeMinus: Dp,
    val largeDefault: Dp,
    val largePlus: Dp
)

val cipherDimensions = CipherDimensions(
    smallMinus = 2.dp,
    smallDefault = 4.dp,
    smallPlus = 8.dp,

    mediumMinus = 12.dp,
    mediumDefault = 16.dp,
    mediumPlus = 20.dp,

    largeMinus = 24.dp,
    largeDefault = 32.dp,
    largePlus = 40.dp
)


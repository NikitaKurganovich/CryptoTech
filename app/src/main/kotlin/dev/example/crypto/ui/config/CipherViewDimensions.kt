package dev.example.crypto.ui.config

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

interface CipherViewDimensions{
    val inputCornerEdgeWidth: Dp
    val inputCornerEdgeHeight: Dp

    val textCornerEdgeWidth: Dp
    val textCornerEdgeHeight: Dp

    val popupCornerEdgeWidth: Dp
    val popupCornerEdgeHeight: Dp

    val dropdownCornerEdgeWidth: Dp
    val dropdownCornerEdgeHeight: Dp

    val borderWidth: Dp

    val fieldBaseHeight: Dp
    val fieldBaseWidth: Dp
}


object StandardViewDimen: CipherViewDimensions{
    override val inputCornerEdgeWidth: Dp
        get() = 63.dp
    override val inputCornerEdgeHeight: Dp
        get() = 9.dp

    override val textCornerEdgeWidth: Dp
        get() = 63.dp
    override val textCornerEdgeHeight: Dp
        get() = 17.dp

    override val popupCornerEdgeWidth: Dp
        get() = 63.dp
    override val popupCornerEdgeHeight: Dp
        get() = 44.dp

    override val dropdownCornerEdgeWidth: Dp
        get() = 63.dp
    override val dropdownCornerEdgeHeight: Dp
        get() = 44.dp

    override val borderWidth: Dp
        get() = 3.dp

    override val fieldBaseHeight: Dp
        get() = 56.dp

    override val fieldBaseWidth: Dp
        get() = 300.dp
}

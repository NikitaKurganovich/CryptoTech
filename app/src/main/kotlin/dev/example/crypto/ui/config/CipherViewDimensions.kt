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

    val popupButtonBorderWidth: Dp

    val fieldBaseHeight: Dp
    val fieldBaseWidth: Dp

    val buttonVerticalPadding: Dp

    val resultBottomIndent: Dp

    val resultFieldWidth: Dp
    val resultFieldHeight: Dp

    val popupButtonWidth: Dp
    val popupButtonHeight: Dp
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

    override val popupButtonBorderWidth: Dp
        get() = 1.dp

    override val fieldBaseHeight: Dp
        get() = 56.dp

    override val fieldBaseWidth: Dp
        get() = 300.dp

    override val buttonVerticalPadding: Dp
        get() = 60.dp

    override val resultBottomIndent: Dp
        get() = 155.dp

    override val resultFieldWidth: Dp
        get() = 300.dp
    override val resultFieldHeight: Dp
        get() = 106.dp

    override val popupButtonWidth: Dp
        get() = 125.dp
    override val popupButtonHeight: Dp
        get() = 48.dp
}

package dev.example.crypto.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.isFinite
import androidx.compose.ui.unit.isUnspecified

class DashedCornerShape(
    val horizontal: Dp,
    val vertical: Dp,
    val dashWidth: Dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        val horizontalOffset = with(density) {
            if (horizontal.isNotCorrect) size.width / 2 else horizontal.toPx()
        }
        val verticalOffset = with(density) {
            if (vertical.isNotCorrect) size.height / 2 else vertical.toPx()
        }
        val width = with(density) { dashWidth.toPx() }
        val basePath = Path()

        basePath.moveTo(0f, 0f)
        basePath.lineTo(horizontalOffset, 0f)
        basePath.lineTo(horizontalOffset, width)
        basePath.lineTo(width, width)
        basePath.lineTo(width, verticalOffset)
        basePath.lineTo(0f, verticalOffset)
        basePath.lineTo(0f, 0f)
        basePath.close()

        basePath.moveTo(size.width - horizontalOffset, 0f)
        basePath.lineTo(size.width, 0f)
        basePath.lineTo(size.width, verticalOffset)
        basePath.lineTo(size.width - width, verticalOffset)
        basePath.lineTo(size.width - width, width)
        basePath.lineTo(size.width - horizontalOffset, width)
        basePath.lineTo(size.width - horizontalOffset, 0f)
        basePath.close()

        basePath.moveTo(size.width, size.height)
        basePath.lineTo(size.width - horizontalOffset, size.height)
        basePath.lineTo(size.width - horizontalOffset, size.height - width)
        basePath.lineTo(size.width - width, size.height - width)
        basePath.lineTo(size.width - width, size.height - verticalOffset)
        basePath.lineTo(size.width, size.height - verticalOffset)
        basePath.lineTo(size.width, size.height)
        basePath.close()

        basePath.moveTo(0f, size.height)
        basePath.lineTo(0f, size.height - verticalOffset)
        basePath.lineTo(width, size.height - verticalOffset)
        basePath.lineTo(width, size.height - width)
        basePath.lineTo(horizontalOffset, size.height - width)
        basePath.lineTo(horizontalOffset, size.height)
        basePath.lineTo(0f, size.height)
        basePath.close()

        return if (width > 0f) {
            Outline.Generic(basePath)
        } else {
            Outline.Rectangle(Rect(0f, 0f, width, width))
        }
    }
}

val Dp.isNotCorrect: Boolean get() =
    !this.isFinite || this.isUnspecified || this == Dp.Hairline

@Composable
fun rememberAnimatedEnabledBorderColor(
    defaultValue: Color,
    activeColor: Color,
    isActive: Boolean
): State<Color> {
    val targetValue by remember(isActive) {
        derivedStateOf {
            if (isActive) {
                activeColor
            } else {
                defaultValue
            }
        }
    }
    return animateColorAsState(
        targetValue = targetValue,
        animationSpec = tween(
            durationMillis = 500,
            easing = EaseInExpo
        ),
        label = ""
    )
}

@Composable
fun rememberAnimatedBorderSize(
    defaultValue: Dp,
    activeValue: Dp,
    isActive: Boolean
): State<Dp> {
    val targetValue by remember(isActive) {
        derivedStateOf {
            if (isActive) {
                activeValue
            } else {
                defaultValue
            }
        }
    }
    return animateDpAsState(
        targetValue = targetValue,
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = ""
    )
}

@Immutable
data class EdgeValues(
    val horizontal: Dp,
    val vertical: Dp
)
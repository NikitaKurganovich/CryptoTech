package dev.example.crypto.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import dev.example.crypto.ui.config.CipherTheme

@Composable
fun Modifier.customBorder(
    edgeColor: Color,
    innerColor: Color,
    width: Dp,
    edges: EdgeValues,
    isFocused: Boolean = false,
    background: Color = CipherTheme.colors.transparentBackground
): Modifier =
    composed {
        val density = LocalDensity.current
        val innerBorderColor by rememberAnimatedEnabledBorderColor(
            isActive = isFocused,
            activeColor = edgeColor,
            defaultValue = innerColor
        )

        var halfOfWidth by remember { mutableStateOf(Dp.Hairline) }
        var halfOfHeight by remember { mutableStateOf(Dp.Hairline) }

        val horizontalEdge by rememberAnimatedBorderSize(
            defaultValue = edges.horizontal,
            activeValue = halfOfWidth,
            isActive = isFocused
        )
        val verticalEdge by rememberAnimatedBorderSize(
            defaultValue = edges.vertical,
            activeValue = halfOfHeight,
            isActive = isFocused
        )
        this.border(
                BorderStroke(
                    width = width,
                    color = innerBorderColor
                ),
            )
            .border(
                BorderStroke(
                    width = width,
                    color = edgeColor
                ),
                shape = DashedCornerShape(
                    horizontal = horizontalEdge,
                    vertical = verticalEdge,
                    dashWidth = width
                )
            )
            .background(background)
            .onPlaced { size ->
                halfOfWidth = with(density) {
                    (size.size.width / 2).toDp()
                }
                halfOfHeight = with(density) {
                    (size.size.height / 2).toDp()
                }
            }
    }



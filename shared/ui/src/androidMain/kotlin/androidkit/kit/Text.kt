package androidkit.kit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import dev.crypto.ui.theme.CipherTheme

@Composable
fun CipherTextWithBord(
    modifier: Modifier = Modifier,
    text: String,
    isError: Boolean = false,
    edges: EdgeValues = EdgeValues(
        horizontal = CipherTheme.viewDimensions.textCornerEdgeWidth,
    vertical = CipherTheme.viewDimensions.textCornerEdgeHeight
    ),
    textStyle: TextStyle = CipherTheme.typography.default,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .size(
                width = CipherTheme.viewDimensions.fieldBaseWidth,
                height = CipherTheme.viewDimensions.fieldBaseHeight
            )
            .customBorder(
                edgeColor = CipherTheme.colors.borderCorner,
                innerColor = CipherTheme.colors.border,
                width = CipherTheme.viewDimensions.borderWidth,
                edges = edges,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = CipherTheme.dimensions.mediumDefault),
            text = text,
            color = if(isError) CipherTheme.colors.textError
            else CipherTheme.colors.text,
            style = textStyle

        )
        trailingIcon?.invoke()
    }
}

@Composable
fun CipherText(
    modifier: Modifier = Modifier,
    text: String,
    isError: Boolean = false,
    textStyle: TextStyle = CipherTheme.typography.default,
    color: Color = CipherTheme.colors.text
) {
    Text(
        modifier = modifier,
        text = text,
        color = if(isError) CipherTheme.colors.textError
        else color,
        style = textStyle
    )
}


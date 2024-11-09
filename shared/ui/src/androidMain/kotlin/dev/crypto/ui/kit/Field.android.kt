package dev.crypto.ui.kit

import androidkit.kit.EdgeValues
import androidkit.kit.customBorder
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import dev.crypto.ui.theme.CipherTheme

@Composable
actual fun CipherOutlinedTextField(
    modifier: Modifier,
    value: String,
    isError: Boolean,
    onValueChange: (String) -> Unit,
    readOnly: Boolean,
    textStyle: TextStyle,
    trailIcon: @Composable (() -> Unit)?,
    placeHolder: @Composable (() -> Unit)?,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) {
    val interaction = remember { MutableInteractionSource() }
    val isFocused by interaction.collectIsFocusedAsState()
    OutlinedTextField(
        modifier = modifier
            .size(
                width = CipherTheme.viewDimensions.fieldBaseWidth,
                height = CipherTheme.viewDimensions.fieldBaseHeight
            )
            .customBorder(
                edgeColor = CipherTheme.colors.borderCorner,
                innerColor = CipherTheme.colors.border,
                width = CipherTheme.viewDimensions.borderWidth,
                edges = EdgeValues(
                    horizontal = CipherTheme.viewDimensions.inputCornerEdgeWidth,
                    vertical = CipherTheme.viewDimensions.inputCornerEdgeHeight
                ),
                isFocused = isFocused
            ),
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        placeholder = placeHolder,
        singleLine = true,
        isError = isError,
        readOnly = readOnly,
        shape = CutCornerShape(Dp.Hairline),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedTextColor = CipherTheme.colors.text,
            unfocusedTextColor = CipherTheme.colors.text,
        ),
        trailingIcon = trailIcon,
        interactionSource = interaction,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}
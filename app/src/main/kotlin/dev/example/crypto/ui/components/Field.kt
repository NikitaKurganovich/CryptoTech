package dev.example.crypto.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import dev.example.crypto.ui.config.CipherTheme
import dev.example.crypto.ui.states.base.FieldState
import dev.example.crypto.ui.states.implementation.FieldStateImpl

@Composable
fun CipherOutlinedTextField(
    modifier: Modifier = Modifier,
    edges: EdgeValues = EdgeValues(
        horizontal = CipherTheme.viewDimensions.inputCornerEdgeWidth,
        vertical = CipherTheme.viewDimensions.inputCornerEdgeHeight
    ),
    inputFieldState: FieldState,
    onValueChange: (String) -> Unit = remember { {} },
    readOnly: Boolean = false,
    textStyle: TextStyle = CipherTheme.typography.default,
    trailIcon: @Composable (() -> Unit)? = null,
    placeHolder: @Composable (() -> Unit)? = null,
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
                edges = edges,
                isFocused = isFocused
            ),
        value = inputFieldState.value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        placeholder = placeHolder,
        singleLine = true,
        isError = inputFieldState.isError,
        readOnly = readOnly,
        shape = CutCornerShape(Dp.Hairline),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedTextColor = CipherTheme.colors.text,
            unfocusedTextColor = CipherTheme.colors.text,
        ),
        trailingIcon = trailIcon,
        interactionSource = interaction
    )
}


@Preview
@Composable
fun FieldPreview() {
    val edgeValues = EdgeValues(
        vertical = CipherTheme.viewDimensions.inputCornerEdgeHeight,
        horizontal = CipherTheme.viewDimensions.inputCornerEdgeWidth
    )
    CipherTheme {
        var state by remember {
            mutableStateOf(
                FieldStateImpl(
                    isError = false,
                    label = "test",
                    value = "",
                    supportingText = "Test",
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.DarkGray)
        ) {
            CipherOutlinedTextField(
                modifier = Modifier
                    .padding(4.dp),
                inputFieldState = state,
                onValueChange = {
                    state = state.onValueChange(it)
                },
                edges = edgeValues,
                placeHolder = {
                    Text(
                        text = state.label,
                        style = CipherTheme.typography.default,
                        color = CipherTheme.colors.hint
                    )
                }
            )
            OutlinedTextField(value = "", onValueChange = {})
        }
    }
}
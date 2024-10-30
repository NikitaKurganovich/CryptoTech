package androidkit.kit

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import dev.crypto.ui.theme.CipherTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@Composable
fun CipherOutlinedTextField(
    modifier: Modifier = Modifier,
    edges: EdgeValues = EdgeValues(
        horizontal = CipherTheme.viewDimensions.inputCornerEdgeWidth,
    vertical = CipherTheme.viewDimensions.inputCornerEdgeHeight
    ),
    value: String,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    readOnly: Boolean = false,
    textStyle: TextStyle = CipherTheme.typography.default,
    trailIcon: @Composable (() -> Unit)? = null,
    placeHolder: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
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

sealed class MockIntent{
    data class TextChange(val value: String): MockIntent()
}

class MockViewModel{
    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()

    fun processIntent(intent: MockIntent){
        when(intent){
            is MockIntent.TextChange -> {
                _state.update { intent.value }
            }
        }
    }
}

@Preview
@Composable
fun FieldPreview() {
    val edgeValues = EdgeValues(
        vertical = CipherTheme.viewDimensions.inputCornerEdgeHeight,
        horizontal = CipherTheme.viewDimensions.inputCornerEdgeWidth
    )
    val viewModel = MockViewModel()
    val state by viewModel.state.collectAsState()
    CipherTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.DarkGray)
        ) {
            CipherOutlinedTextField(
                modifier = Modifier
                    .padding(4.dp),
                value = state,
                onValueChange = {
                    viewModel.processIntent(MockIntent.TextChange(it))
                },
                edges = edgeValues,
                placeHolder = {
                    Text(
                        text = "state.label",
                        style = CipherTheme.typography.default,
                        color = CipherTheme.colors.hint
                    )
                }
            )
        }
    }
}
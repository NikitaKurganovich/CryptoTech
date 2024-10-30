package dev.crypto.first.ui.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.crypto.first.ui.states.base.InputFieldState

@Composable
fun CipherOutlinedTextField(
    modifier: Modifier = Modifier,
    inputFieldState: InputFieldState,
    onValueChange: (String) -> Unit,
){
    OutlinedTextField(
        modifier = modifier,
        label = {
            TextFieldLabel(
                textState = inputFieldState
            )
        },
        value = inputFieldState.value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        maxLines = 1,
        isError = inputFieldState.isError
    )
}
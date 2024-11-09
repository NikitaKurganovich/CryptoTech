package dev.crypto.ui.kit

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

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
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        readOnly = readOnly,
        textStyle = textStyle,
        trailingIcon = trailIcon,
        placeholder = placeHolder,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}
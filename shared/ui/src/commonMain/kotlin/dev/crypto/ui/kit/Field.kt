package dev.crypto.ui.kit

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import dev.crypto.ui.theme.CipherTheme

@Composable
expect fun CipherOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    readOnly: Boolean = false,
    textStyle: TextStyle = CipherTheme.typography.default,
    trailIcon: @Composable (() -> Unit)? = null,
    placeHolder: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
)
package dev.example.crypto.ui.states.implementation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.example.crypto.ui.states.base.KeyFieldState

class KeyFieldStateImpl(
    override val value: String,
    override val label: String,
    override val supportingText: String = "",
    override val isError: Boolean = false,
    private val aboutKeyId: Int
) : KeyFieldState {

    override val aboutKey: String
        @Composable
        get() = stringResource(id = aboutKeyId)

    override fun onValueChange(newValue: String): KeyFieldStateImpl =
        KeyFieldStateImpl(
            value = newValue,
            label = label,
            supportingText = supportingText,
            isError = isError,
            aboutKeyId = aboutKeyId
        )

    override fun onError(): KeyFieldStateImpl =
        KeyFieldStateImpl(
            value = value,
            label = label,
            supportingText = supportingText,
            isError = true,
            aboutKeyId = aboutKeyId
        )
}

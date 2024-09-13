package dev.example.crypto.ui.states.implementation

import dev.example.crypto.ui.states.base.InputFieldState

class InputFieldStateImpl(
    override val label: String,
    override val isError: Boolean = false,
    override val supportingText: String = "",
    override val value: String
) : InputFieldState {

    override fun onValueChange(newValue: String): InputFieldState =
        InputFieldStateImpl(
            value = newValue,
            label = label,
            isError = false
        )

    override fun onError(): InputFieldState =
        InputFieldStateImpl(
            value = value,
            isError = true,
            label = label
        )
}
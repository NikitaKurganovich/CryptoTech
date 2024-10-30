package dev.crypto.first.ui.states.base

interface InputFieldState: TextState {
    val supportingText: String
    val value: String

    fun onValueChange(newValue: String): InputFieldState
    fun onError(): InputFieldState
}
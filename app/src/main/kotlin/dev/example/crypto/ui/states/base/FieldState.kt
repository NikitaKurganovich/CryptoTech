package dev.example.crypto.ui.states.base

interface FieldState: TextState {
    val supportingText: String
    val value: String
    fun onValueChange(newValue: String): FieldState
    fun onError(): FieldState
}
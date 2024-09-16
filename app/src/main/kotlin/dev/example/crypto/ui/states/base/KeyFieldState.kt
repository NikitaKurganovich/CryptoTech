package dev.example.crypto.ui.states.base

import androidx.compose.runtime.Composable

interface KeyFieldState: FieldState {
    @get:Composable
    val aboutKey: String

    override fun onValueChange(newValue: String): KeyFieldState
}
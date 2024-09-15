package dev.example.crypto.ui

import dev.example.crypto.ui.states.base.FieldState
import dev.example.crypto.ui.states.base.TextState

data class MainScreenState(
    val keyInputFieldState: FieldState,
    val messageInputFieldState: FieldState,
    val infoTextState: TextState,
    val resultTextState: TextState,
    val currentMethod: Ciphers = Ciphers.CESAR
)

enum class Ciphers(val cipher: String) {
    CESAR("Cesar"),
    SIMPLE_REPLACEMENT("Simple Replacement"),
    VIGENERE("Vigenere"),
    SIMPLE_PERMUTATION("Simple Permutation"),
    COMPLICATED_PERMUTATION("Complicated Permutation")
}
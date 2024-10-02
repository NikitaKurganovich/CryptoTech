package dev.example.crypto.ui

import androidkit.states.base.FieldState
import androidkit.states.base.KeyFieldState
import androidkit.states.base.TextState

data class MainScreenState(
    val keyInputFieldState: KeyFieldState,
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
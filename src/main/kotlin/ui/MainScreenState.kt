package ui

import ui.states.base.InputFieldState
import ui.states.base.TextState

data class MainScreenState(
    val keyInputFieldState: InputFieldState,
    val messageInputFieldState: InputFieldState,
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
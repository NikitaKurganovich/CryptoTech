package ui

import ui.states.base.InputFieldState
import ui.states.base.TextState

data class MainScreenState(
    val keyInputFieldState: InputFieldState,
    val messageInputFieldState: InputFieldState,
    val infoTextState: TextState,
    val resultTextState: TextState,
    val currentMethod: Ciphers = Ciphers.Cesar
)

enum class Ciphers{
    Cesar,
    EasyReplacement,
    Vigenere,
    SimplePermutation,
    ComplicatedPermutation
}
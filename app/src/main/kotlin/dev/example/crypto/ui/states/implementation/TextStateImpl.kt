package dev.example.crypto.ui.states.implementation

import dev.example.crypto.ui.states.base.TextState

data class TextStateImpl(
    override val isError: Boolean,
    override val label: String
): TextState

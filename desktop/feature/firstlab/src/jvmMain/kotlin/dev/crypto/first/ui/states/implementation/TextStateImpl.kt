package dev.crypto.first.ui.states.implementation

import dev.crypto.first.ui.states.base.TextState

data class TextStateImpl(
    override val isError: Boolean,
    override val label: String
): TextState

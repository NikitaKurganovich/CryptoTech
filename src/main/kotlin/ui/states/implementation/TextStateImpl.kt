package ui.states.implementation

import ui.states.base.TextState

data class TextStateImpl(
    override val isError: Boolean,
    override val label: String
): TextState

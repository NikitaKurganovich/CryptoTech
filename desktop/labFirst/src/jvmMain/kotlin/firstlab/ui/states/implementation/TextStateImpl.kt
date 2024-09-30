package firstlab.ui.states.implementation

import firstlab.ui.states.base.TextState

data class TextStateImpl(
    override val isError: Boolean,
    override val label: String
): TextState

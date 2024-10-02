package androidkit.states.implementation

import androidkit.states.base.TextState

data class TextStateImpl(
    override val isError: Boolean,
    override val label: String
): TextState

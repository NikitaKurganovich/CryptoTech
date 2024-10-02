package androidkit.states.implementation

import androidkit.states.base.FieldState

class FieldStateImpl(
    override val label: String,
    override val isError: Boolean = false,
    override val supportingText: String = "",
    override val value: String,
) : FieldState {

    override fun onValueChange(newValue: String): FieldStateImpl =
        FieldStateImpl(
            value = newValue,
            label = label,
            isError = false,
        )

    override fun onError(): FieldStateImpl =
        FieldStateImpl(
            value = value,
            isError = true,
            label = label,
        )
}
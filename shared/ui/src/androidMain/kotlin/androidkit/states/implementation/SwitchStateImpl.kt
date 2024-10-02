package androidkit.states.implementation

import androidkit.states.base.SwitchState

data class SwitchStateImpl(
    override val firstOption: String,
    override val secondOption: String,
    override val isEnabled: Boolean = true
): SwitchState {
    override fun onValueChange(): SwitchState =
        this.copy(isEnabled = !isEnabled)
}
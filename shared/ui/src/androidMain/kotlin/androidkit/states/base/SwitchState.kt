package androidkit.states.base

interface SwitchState {
    val isEnabled: Boolean
    val firstOption: String
    val secondOption: String
    fun onValueChange(): SwitchState
}
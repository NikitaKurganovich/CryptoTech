package androidkit.states.implementation

import androidx.compose.runtime.Composable
import androidkit.states.base.KeyFieldState
import androidx.compose.ui.res.stringResource

class KeyFieldStateImpl(
    override val value: String,
    override val label: String,
    override val supportingText: String = "",
    override val isError: Boolean = false,
    private val aboutKeyRes: Int
) : KeyFieldState {

    override val aboutKey: String
        @Composable
        get() = stringResource(aboutKeyRes)

    override fun onValueChange(newValue: String): KeyFieldStateImpl =
        KeyFieldStateImpl(
            value = newValue,
            label = label,
            supportingText = supportingText,
            isError = isError,
            aboutKeyRes = aboutKeyRes
        )

    override fun onError(): KeyFieldStateImpl =
        KeyFieldStateImpl(
            value = value,
            label = label,
            supportingText = supportingText,
            isError = true,
            aboutKeyRes = aboutKeyRes
        )
}

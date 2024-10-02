package androidkit.kit

import androidkit.states.base.SwitchState
import androidkit.states.implementation.TextStateImpl
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CipherSwitch(
    modifier: Modifier = Modifier,
    switchState: SwitchState
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CipherText(
            textState = TextStateImpl(
                label = switchState.firstOption,
                isError = false
            )
        )
    }
}


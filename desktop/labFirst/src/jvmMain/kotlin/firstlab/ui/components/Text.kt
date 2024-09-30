package firstlab.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import firstlab.ui.states.base.TextState

@Composable
fun TextFieldLabel(
    modifier: Modifier = Modifier,
    textState: TextState
) {
    Text(
        modifier = modifier,
        text = textState.label,
        color = if(textState.isError) Color.Red else Color.Black
    )
}


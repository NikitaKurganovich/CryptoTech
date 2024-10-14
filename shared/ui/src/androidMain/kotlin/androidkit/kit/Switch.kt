package androidkit.kit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CipherSwitch(
    modifier: Modifier = Modifier,
    firstOptionText: String,
    secondOptionText: String,
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CipherText(
            text = firstOptionText,
        )
        CipherText(
            text = secondOptionText,
        )
    }
}


package androidkit.kit

import androidkit.theme.CipherTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp

@Composable
fun CipherButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
){
    Button(
        modifier = modifier
            .size(
                height = CipherTheme.viewDimensions.fieldBaseHeight,
                width = CipherTheme.viewDimensions.fieldBaseWidth
            ),
        shape = CutCornerShape(Dp.Hairline),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = CipherTheme.colors.buttonFilled
        )
    ) {
        Text(
            modifier = Modifier,
            textAlign = TextAlign.Center,
            text = text,
            color = CipherTheme.colors.text,
            style = CipherTheme.typography.default
        )
    }
}

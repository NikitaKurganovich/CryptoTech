package dev.crypto.ui.kit

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.crypto.ui.theme.CipherTheme

@Composable
actual fun CipherResultView(
    modifier: Modifier,
    infoText: String,
    resultText: String,
    isError: Boolean
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = infoText,
            style = CipherTheme.typography.bold,
        )
        Box(
            modifier = Modifier
                .size(
                    width = 400.dp,
                    height = 200.dp
                )
                .border(
                    width = 1.dp,
                    color = if (isError) CipherTheme.colors.textError else CipherTheme.colors.text,
                    shape = RoundedCornerShape(CipherTheme.dimensions.mediumDefault)
                ),
            contentAlignment = Alignment.Center
        ){
            SelectionContainer {
                Text(
                    text = resultText,
                    style = CipherTheme.typography.default,
                    color = if (isError) CipherTheme.colors.textError else CipherTheme.colors.text
                )
            }
        }
    }
}
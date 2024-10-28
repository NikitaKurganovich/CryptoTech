package androidkit.kit

import androidkit.theme.CipherTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CipherResultView(
    modifier: Modifier = Modifier,
    infoText: String,
    resultText: String,
    isError: Boolean
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        CipherText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = CipherTheme.dimensions.smallPlus),
            text = infoText
        )
        CipherTextWithBord(
            modifier = Modifier
                .fillMaxWidth()
                .size(
                    width = CipherTheme.viewDimensions.resultFieldWidth,
                    height = CipherTheme.viewDimensions.resultFieldHeight
                )
                .padding(top = CipherTheme.dimensions.smallPlus),
            text = resultText,
            isError = isError
        )
    }
}
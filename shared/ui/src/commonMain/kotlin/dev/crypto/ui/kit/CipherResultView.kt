package dev.crypto.ui.kit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import crypto_decoding.shared.ui.generated.resources.Res
import crypto_decoding.shared.ui.generated.resources.default_result_text
import org.jetbrains.compose.resources.stringResource

@Composable
expect fun CipherResultView(
    modifier: Modifier = Modifier,
    infoText: String = stringResource(Res.string.default_result_text),
    resultText: String,
    isError: Boolean = false
)
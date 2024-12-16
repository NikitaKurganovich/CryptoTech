package dev.crypto.first

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import crypto_decoding.desktop.feature.firstlab.generated.resources.key_field_label
import crypto_decoding.desktop.feature.firstlab.generated.resources.message_field_label
import dev.crypto.labfirst.Ciphers
import dev.crypto.labfirst.FirstLabIntent
import dev.crypto.labfirst.FirstLabRes
import dev.crypto.labfirst.FirstLabState
import dev.crypto.labfirst.resources.Res
import dev.crypto.labfirst.resources.first_decrypt
import dev.crypto.labfirst.resources.first_encrypt
import dev.crypto.labfirst.resources.first_ok
import dev.crypto.ui.kit.CipherResultView
import dev.crypto.ui.kit.CipherSwitch
import dev.crypto.ui.theme.CipherTheme
import org.jetbrains.compose.resources.stringResource
import crypto_decoding.desktop.feature.firstlab.generated.resources.Res as LocalRes

class FirstLabScreen : Screen {
    @Composable
    override fun Content() {
        val model = rememberScreenModel { FirstLabModel() }
        val state by model.state.collectAsState()
        ScreenContent(
            modifier = Modifier,
            onIntent = model::onIntent,
            state = state
        )
    }

    @Composable
    private fun ScreenContent(
        modifier: Modifier = Modifier,
        onIntent: (FirstLabIntent) -> Unit,
        state: FirstLabState,
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(80.dp)
            ) {
                CipherMethodSelection(
                    modifier = Modifier,
                    onCipherMethodChange = { onIntent(FirstLabIntent.ChangeCipher(it)) },
                )
                WorkingArea(
                    modifier = Modifier,
                    onIntent = onIntent,
                    state = remember(state) { state }
                )
                CipherResultView(
                    modifier = Modifier,
                    resultText = state.resultText.getString(),
                    isError = state.isErrorResult
                )
            }
        }
    }

    @Composable
    private fun CipherMethodSelection(
        modifier: Modifier = Modifier,
        onCipherMethodChange: (Ciphers) -> Unit,
    ) {
        val options = Ciphers.entries
        Column(
            modifier = modifier
        ) {
            val (selectedOption, onOptionSelected) = remember { mutableStateOf(options.first()) }

            options.forEach { cipher ->
                Row(
                    Modifier
                        .clip(RoundedCornerShape(CipherTheme.dimensions.smallDefault))
                        .selectable(
                            selected = (cipher == selectedOption),
                            onClick = {
                                onCipherMethodChange(cipher)
                                onOptionSelected(cipher)
                            }
                        )
                        .padding(horizontal = CipherTheme.dimensions.smallDefault),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (cipher == selectedOption),
                        onClick = { onOptionSelected(cipher) }
                    )
                    Text(
                        text = cipher.cipher,
                        style = CipherTheme.typography.default,
                        modifier = Modifier.padding(start = CipherTheme.dimensions.smallDefault)
                    )
                }
            }
        }
    }

    @Composable
    private fun WorkingArea(
        modifier: Modifier = Modifier,
        onIntent: (FirstLabIntent) -> Unit,
        state: FirstLabState,
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                modifier = Modifier.padding(CipherTheme.dimensions.smallDefault),
                value = state.messageInputFieldText,
                label = {
                    Text(
                        text = stringResource(LocalRes.string.message_field_label),
                        style = CipherTheme.typography.default,
                    )
                },
                onValueChange = { newValue ->
                    onIntent(FirstLabIntent.MessageFieldChange(newValue))
                }
            )
            OutlinedTextField(
                modifier = Modifier.padding(CipherTheme.dimensions.smallDefault),
                value = state.keyInputFieldText,
                label = {
                    Text(
                        text = stringResource(LocalRes.string.key_field_label),
                        style = CipherTheme.typography.default,
                    )
                },
                onValueChange = { newValue ->
                    onIntent(FirstLabIntent.KeyFieldChange(newValue))
                }
            )
            CipherSwitch(
                firstOptionText = stringResource(Res.string.first_decrypt),
                secondOptionText = stringResource(Res.string.first_encrypt),
                isFirst = state.isDecryption,
                onClick = { onIntent(FirstLabIntent.SwitchChange(it)) }
            )
            Button(
                modifier = Modifier
                    .padding(CipherTheme.dimensions.mediumMinus),
                onClick = { onIntent(FirstLabIntent.Act) }
            ) {
                Text(
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    text = stringResource(FirstLabRes.first_ok),
                    style = CipherTheme.typography.default
                )
            }
        }
    }
}
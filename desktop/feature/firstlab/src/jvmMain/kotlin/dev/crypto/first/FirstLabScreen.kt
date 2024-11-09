package dev.crypto.first

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import dev.crypto.base.resources.ResultMessage
import dev.crypto.labfirst.Ciphers
import dev.crypto.labfirst.FirstLabIntent
import dev.crypto.labfirst.FirstLabRes
import dev.crypto.labfirst.FirstLabState
import dev.crypto.labfirst.resources.first_mode
import dev.crypto.labfirst.resources.first_ok
import dev.crypto.labfirst.resources.first_result
import dev.crypto.ui.theme.CipherTheme
import org.jetbrains.compose.resources.stringResource

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
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CipherMethodSelection(
                modifier = Modifier.fillMaxWidth(0.4f),
                onCipherMethodChange = { onIntent(FirstLabIntent.ChangeCipher(it) )},
                onCryptoMethodChange = { onIntent(FirstLabIntent.SwitchChange(it) )},
                state = remember(state) { state }
            )
            WorkingArea(
                modifier = Modifier.fillMaxWidth(0.6f),
                onIntent = onIntent,
                state = remember(state) { state }
            )
        }
    }

    @Composable
    private fun CipherMethodSelection(
        modifier: Modifier = Modifier,
        onCipherMethodChange: (Ciphers) -> Unit,
        onCryptoMethodChange: (Boolean) -> Unit,
        state: FirstLabState
    ) {
        val options = Ciphers.entries
        Column(
            modifier = modifier
        ) {
            val (selectedOption, onOptionSelected) = remember { mutableStateOf(options.first()) }

            options.forEach { cipher ->
                Row(
                    Modifier
                        .fillMaxWidth()
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
                        style = CipherTheme.typography.bold.merge(),
                        modifier = Modifier.padding(start = CipherTheme.dimensions.smallDefault)
                    )
                }
            }

            val checked by remember(state.isDecryption) {
                mutableStateOf(state.isDecryption)
            }

            Spacer(Modifier.height(CipherTheme.dimensions.smallDefault))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = CipherTheme.dimensions.smallDefault),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(FirstLabRes.first_mode),
                    style = CipherTheme.typography.bold.merge()
                )
                Spacer(Modifier.width(CipherTheme.dimensions.smallDefault))
                Switch(
                    checked = checked,
                    onCheckedChange = onCryptoMethodChange
                )
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
                onValueChange = { newValue ->
                    onIntent(FirstLabIntent.MessageFieldChange(newValue))
                }
            )
            OutlinedTextField(
                modifier = Modifier.padding(CipherTheme.dimensions.smallDefault),
                value = state.keyInputFieldText,
                onValueChange = { newValue ->
                    onIntent(FirstLabIntent.KeyFieldChange(newValue))
                }
            )
            Button(
                modifier = Modifier
                    .padding(CipherTheme.dimensions.mediumMinus),
                onClick = { onIntent(FirstLabIntent.Act) }
            ) {
                Text(
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    text = stringResource(FirstLabRes.first_ok)
                )
            }
            ResultView(
                modifier = Modifier,
                infoTextState = stringResource(FirstLabRes.first_result),
                resultTextState = remember(state) { state.resultText }
            )
        }
    }

    @Composable
    private fun ResultView(
        modifier: Modifier = Modifier,
        infoTextState: String,
        resultTextState: ResultMessage,
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CipherTheme.dimensions.smallDefault),
                text = infoTextState
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CipherTheme.dimensions.smallDefault),
                text = resultTextState.getString()
            )
        }
    }
}
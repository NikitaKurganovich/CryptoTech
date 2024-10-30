package dev.crypto.first.ui

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
import crypto_decoding.desktop.feature.firstlab.generated.resources.Res
import crypto_decoding.desktop.feature.firstlab.generated.resources.mode
import crypto_decoding.desktop.feature.firstlab.generated.resources.welcome
import dev.crypto.first.ui.components.CipherOutlinedTextField
import dev.crypto.first.ui.components.TextFieldLabel
import dev.crypto.first.ui.states.base.TextState
import dev.crypto.first.ui.states.implementation.CryptoButtonState
import dev.crypto.first.ui.states.implementation.TextStateImpl
import dev.crypto.ui.theme.CipherTheme
import org.jetbrains.compose.resources.stringResource

class FirstLabScreen : Screen {
    @Composable
    override fun Content() {
        val model = rememberScreenModel { FirstLabModel() }
        val state by model.state.collectAsState()
        ScreenContent(
            modifier = Modifier,
            model = model,
            state = state
        )
    }

    @Composable
    private fun ScreenContent(
        modifier: Modifier = Modifier,
        model: FirstLabModel,
        state: FirstLabState,
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CipherMethodSelection(
                modifier = Modifier.fillMaxWidth(0.4f),
                onCipherMethodChange = remember { model::onCipherChange },
                onCryptoMethodChange = remember { model::onCryptoMethodChange },
                state = state
            )
            WorkingArea(
                modifier = Modifier.fillMaxWidth(0.6f),
                model = model,
                state = state
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

            val checked by remember(state.buttonState) {
                mutableStateOf(state.buttonState is CryptoButtonState.Encrypt)
            }

            Spacer(Modifier.height(CipherTheme.dimensions.smallDefault))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = CipherTheme.dimensions.smallDefault),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.mode),
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
        model: FirstLabModel,
        state: FirstLabState,
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextFieldLabel(
                modifier = Modifier,
                textState = TextStateImpl(
                    label = stringResource(Res.string.welcome),
                    isError = false
                )
            )
            CipherOutlinedTextField(
                modifier = Modifier.padding(CipherTheme.dimensions.smallDefault),
                inputFieldState = state.messageInputFieldState,
                onValueChange = { newValue ->
                    model.onMessageFieldValueChange(newValue)
                }
            )
            CipherOutlinedTextField(
                modifier = Modifier.padding(CipherTheme.dimensions.smallDefault),
                inputFieldState = state.keyInputFieldState,
                onValueChange = { newValue ->
                    model.onKeyFieldValueChange(newValue)
                }
            )
            Button(
                modifier = Modifier
                    .padding(CipherTheme.dimensions.mediumMinus),
                onClick = remember(state.buttonState) { { state.buttonState.onClick() } }
            ) {
                Text(
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    text = remember(state.buttonState) { state.buttonState.label }
                )
            }
            ResultView(
                modifier = Modifier,
                infoTextState = state.infoTextState,
                resultTextState = state.resultTextState
            )
        }
    }

    @Composable
    private fun ResultView(
        modifier: Modifier = Modifier,
        infoTextState: TextState,
        resultTextState: TextState,
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
        ) {
            TextFieldLabel(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CipherTheme.dimensions.smallDefault),
                textState = infoTextState
            )
            TextFieldLabel(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CipherTheme.dimensions.smallDefault),
                textState = resultTextState
            )
        }
    }
}
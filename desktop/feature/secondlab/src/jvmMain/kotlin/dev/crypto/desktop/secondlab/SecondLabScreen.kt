package dev.crypto.desktop.secondlab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import dev.crypto.android.labsecond.ui.SecondLabScreenState
import dev.crypto.labsecond.CryptoMode
import dev.crypto.labsecond.SecondLabIntent
import dev.crypto.labsecond.SecondLabString
import dev.crypto.labsecond.resources.second_button_action
import dev.crypto.labsecond.resources.second_cipher_switch_decrypt
import dev.crypto.labsecond.resources.second_cipher_switch_encrypt
import dev.crypto.labsecond.resources.second_e_key_placeholder
import dev.crypto.labsecond.resources.second_message_placeholder
import dev.crypto.labsecond.resources.second_p_key_placeholder
import dev.crypto.labsecond.resources.second_q_key_placeholder
import dev.crypto.ui.kit.CipherResultView
import dev.crypto.ui.kit.CipherSwitch
import dev.crypto.ui.theme.CipherTheme
import org.jetbrains.compose.resources.stringResource

class SecondLabScreen : Screen {
    @Composable
    override fun Content() {
        val model = rememberScreenModel { SecondLabScreenModel() }
        val state by model.state.collectAsState()
        ScreenContent(
            state = state,
            onIntentReceived = model::onNewIntent
        )
    }

    @Composable
    fun ScreenContent(
        state: SecondLabScreenState,
        onIntentReceived: (SecondLabIntent) -> Unit,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(CipherTheme.dimensions.largePlus)
            ) {
                InputFields(
                    state = remember(state) { state },
                    onIntent = onIntentReceived
                )
                CipherResultView(
                    isError = state.isError,
                    resultText = state.result.getString()
                )
            }
        }
    }

    @Composable
    private fun InputFields(
        modifier: Modifier = Modifier,
        state: SecondLabScreenState,
        onIntent: (SecondLabIntent) -> Unit
    ) {
        val outlinedFieldModifier = Modifier
            .padding(vertical = CipherTheme.dimensions.mediumMinus)

        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = outlinedFieldModifier,
                value = state.messageFieldValue,
                onValueChange = { onIntent(SecondLabIntent.MessageFieldChange(it)) },
                label = {
                    Text(
                        text = stringResource(SecondLabString.second_message_placeholder),
                        style = CipherTheme.typography.default
                    )
                }
            )
            OutlinedTextField(
                modifier = outlinedFieldModifier,
                value = state.pKeyFieldValue,
                onValueChange = { onIntent(SecondLabIntent.PKeyFieldChange(it)) },
                label = {
                    Text(
                        text = stringResource(SecondLabString.second_p_key_placeholder),
                        style = CipherTheme.typography.default
                    )
                }
            )
            OutlinedTextField(
                modifier = outlinedFieldModifier,
                value = state.qKeyFieldValue,
                onValueChange = { onIntent(SecondLabIntent.QKeyFieldChange(it)) },
                label = {
                    Text(
                        text = stringResource(SecondLabString.second_q_key_placeholder),
                        style = CipherTheme.typography.default
                    )
                }
            )
            OutlinedTextField(
                modifier = outlinedFieldModifier,
                value = state.eKeyFieldValue,
                onValueChange = { onIntent(SecondLabIntent.EKeyFieldChange(it)) },
                label = {
                    Text(
                        text = stringResource(SecondLabString.second_e_key_placeholder),
                        style = CipherTheme.typography.default
                    )
                }
            )
            CipherSwitch(
                firstOptionText = stringResource(SecondLabString.second_cipher_switch_encrypt),
                secondOptionText = stringResource(SecondLabString.second_cipher_switch_decrypt) ,
                isFirst = remember(state) { state.encryptionMode == CryptoMode.Encrypting },
                onClick = { onIntent(SecondLabIntent.SwitchChange(it)) }
            )
            Button(
                modifier = Modifier
                    .padding(CipherTheme.dimensions.mediumMinus),
                onClick = { onIntent(SecondLabIntent.Perform) }
            ) {
                Text(
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    text = stringResource(SecondLabString.second_button_action),
                    style = CipherTheme.typography.default
                )
            }
        }
    }
}
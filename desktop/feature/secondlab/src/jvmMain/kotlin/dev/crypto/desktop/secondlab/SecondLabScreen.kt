package dev.crypto.desktop.secondlab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.style.LineHeightStyle
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
import dev.crypto.labsecond.resources.second_result
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
        val scroll = rememberScrollState(0)
        Column(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .verticalScroll(scroll),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(stringResource(SecondLabString.second_cipher_switch_encrypt))
                Switch(
                    modifier = Modifier,
                    checked = state.encryptionMode == CryptoMode.Encrypting,
                    onCheckedChange = { onIntentReceived(SecondLabIntent.SwitchChange(it)) }
                )
                Text(stringResource(SecondLabString.second_cipher_switch_decrypt))
            }

            InputFields(
                state = remember(state) { state },
                onIntentReceived = onIntentReceived
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { onIntentReceived(SecondLabIntent.Perform) }
            ) {
                Text(text = stringResource(SecondLabString.second_button_action))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = CipherTheme.dimensions.mediumPlus)
            ) {
                Text(stringResource(SecondLabString.second_result))
                SelectionContainer {
                    Text(
                        text = state.result.getString(),
                        color = if (state.isError) CipherTheme.colors.textError
                        else Black
                    )
                }
            }

        }
    }

    @Composable
    private fun ColumnScope.InputFields(
        state: SecondLabScreenState,
        onIntentReceived: (SecondLabIntent) -> Unit
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = CipherTheme.dimensions.mediumMinus),
            value = state.messageFieldValue,
            onValueChange = {
                onIntentReceived(SecondLabIntent.MessageFieldChange(it))
            },
            placeholder = {
                Text(
                    text = stringResource(SecondLabString.second_message_placeholder),
                    color = CipherTheme.colors.hint
                )
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = CipherTheme.dimensions.mediumMinus),
            value = state.pKeyFieldValue,
            onValueChange = {
                onIntentReceived(SecondLabIntent.PKeyFieldChange(it))
            },
            placeholder = {
                Text(
                    text = stringResource(SecondLabString.second_p_key_placeholder),
                    color = CipherTheme.colors.hint
                )
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = CipherTheme.dimensions.mediumMinus),
            value = state.qKeyFieldValue,
            onValueChange = {
                onIntentReceived(SecondLabIntent.QKeyFieldChange(it))
            },
            placeholder = {
                Text(
                    text = stringResource(SecondLabString.second_q_key_placeholder),
                    color = CipherTheme.colors.hint
                )
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = CipherTheme.dimensions.mediumMinus),
            value = state.eKeyFieldValue,
            onValueChange = {
                onIntentReceived(SecondLabIntent.EKeyFieldChange(it))
            },
            placeholder = {
                Text(
                    text = stringResource(SecondLabString.second_e_key_placeholder),
                    color = CipherTheme.colors.hint
                )
            }
        )
    }
}
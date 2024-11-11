package dev.crypto.android.labsecond.ui

import androidkit.components.CipherScreen
import androidkit.kit.CipherOutlinedTextField
import androidkit.kit.CipherSwitch
import androidkit.kit.CipherText
import dev.crypto.ui.theme.CipherTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
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
import dev.crypto.ui.kit.CipherButton
import dev.crypto.ui.kit.CipherResultView
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = viewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    CipherScreen {
        ScreenContent(
            state = state,
            onIntentReceived = { newIntent ->
                viewModel.processIntent(newIntent)
            }
        )
    }
}

@Composable
fun ScreenContent(
    state: SecondLabScreenState,
    onIntentReceived: (SecondLabIntent) -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = CipherTheme.dimensions.mediumDefault,
                vertical = CipherTheme.dimensions.largePlus
            )
    ) {
        CipherSwitch(
            modifier = Modifier
                .fillMaxWidth(),
            firstOptionText = stringResource(SecondLabString.second_cipher_switch_encrypt),
            secondOptionText = stringResource(SecondLabString.second_cipher_switch_decrypt),
            isFirstSelected = state.encryptionMode == CryptoMode.Encrypting,
            onClick = { onIntentReceived(SecondLabIntent.SwitchChange(it)) }
        )
        InputFields(
            state = remember(state) { state },
            onIntentReceived = onIntentReceived
        )
        CipherButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(SecondLabString.second_button_action),
            onClick =  { onIntentReceived(SecondLabIntent.Perform) }
        )
        SelectionContainer {
            CipherResultView(
                modifier = Modifier
                    .fillMaxWidth(),
                infoText = stringResource(SecondLabString.second_result),
                resultText = state.result.getString(),
                isError = remember(state.isError) { state.isError }
            )
        }
    }
}

@Composable
private fun ColumnScope.InputFields(
    state: SecondLabScreenState,
    onIntentReceived: (SecondLabIntent) -> Unit
) {
    CipherOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = CipherTheme.dimensions.mediumMinus),
        value = state.messageFieldValue,
        onValueChange = {
            onIntentReceived(SecondLabIntent.MessageFieldChange(it))
        },
        placeHolder = {
            CipherText(
                text = stringResource(SecondLabString.second_message_placeholder),
                color = CipherTheme.colors.hint
            )
        }
    )
    CipherOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = CipherTheme.dimensions.mediumMinus),
        value = state.pKeyFieldValue,
        onValueChange = {
            onIntentReceived(SecondLabIntent.PKeyFieldChange(it))
        },
        placeHolder = {
            CipherText(
                text = stringResource(SecondLabString.second_p_key_placeholder),
                color = CipherTheme.colors.hint
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
    CipherOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = CipherTheme.dimensions.mediumMinus),
        value = state.qKeyFieldValue,
        onValueChange = {
            onIntentReceived(SecondLabIntent.QKeyFieldChange(it))
        },
        placeHolder = {
            CipherText(
                text = stringResource(SecondLabString.second_q_key_placeholder),
                color = CipherTheme.colors.hint
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
    CipherOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = CipherTheme.dimensions.mediumMinus),
        value = state.eKeyFieldValue,
        onValueChange = {
            onIntentReceived(SecondLabIntent.EKeyFieldChange(it))
        },
        placeHolder = {
            CipherText(
                text = stringResource(SecondLabString.second_e_key_placeholder),
                color = CipherTheme.colors.hint
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
}

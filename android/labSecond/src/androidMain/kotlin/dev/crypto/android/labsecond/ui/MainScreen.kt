package dev.crypto.android.labsecond.ui

import androidkit.components.CipherScreen
import androidkit.kit.CipherButton
import androidkit.kit.CipherOutlinedTextField
import androidkit.kit.CipherResultView
import androidkit.kit.CipherSwitch
import androidkit.kit.CipherText
import androidkit.theme.CipherTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.crypto.labsecond.R

@Composable
fun MainScreen(viewModel: MainScreenViewModel = viewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CipherScreen {
        ScreenContent(
            state = state,
            onIntentReceived = { viewModel::processIntent }
        )
    }
}

@Composable
fun ScreenContent(
    state: MainScreenState,
    onIntentReceived: (MainScreenIntent) -> Unit,
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
            firstOptionText = stringResource(id = R.string.second_cipher_switch_encrypt),
            secondOptionText = stringResource(id = R.string.second_cipher_switch_decrypt),
            isFirstSelected = state.isEncrypting,
            onClick = remember { { onIntentReceived(MainScreenIntent.SwitchChange(it)) } }
        )
        InputFields(
            state = state,
            onIntentReceived = onIntentReceived
        )
        CipherButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(R.string.second_button_action),
            onClick = remember { { onIntentReceived(MainScreenIntent.Perform) } }
        )
        CipherResultView(
            modifier = Modifier
                .fillMaxWidth(),
            infoText = stringResource(R.string.second_result),
            resultText = remember(state.result) { state.result },
            isError = remember(state.isError) { state.isError }
        )
    }
}

@Composable
private fun ColumnScope.InputFields(
    state: MainScreenState,
    onIntentReceived: (MainScreenIntent) -> Unit
) {
    CipherOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = CipherTheme.dimensions.mediumMinus),
        value =  state.messageFieldValue,
        onValueChange = {
            onIntentReceived(MainScreenIntent.MessageFieldChange(it))
        },
        placeHolder = {
            CipherText(text = stringResource(R.string.second_message_placeholder))
        }
    )
    CipherOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = CipherTheme.dimensions.mediumMinus),
        value = state.pKeyFieldValue,
        onValueChange = {
            onIntentReceived(MainScreenIntent.PKeyFieldChange(it))
        },
        placeHolder = {
            CipherText(text = stringResource(R.string.second_p_key_placeholder))
        }
    )
    CipherOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = CipherTheme.dimensions.mediumMinus),
        value = state.qKeyFieldValue,
        onValueChange = {
            onIntentReceived(MainScreenIntent.QKeyFieldChange(it))
        },
        placeHolder = {
            CipherText(text = stringResource(R.string.second_q_key_placeholder))
        }
    )
    CipherOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = CipherTheme.dimensions.mediumMinus),
        value = state.eKeyFieldValue,
        onValueChange = {
            onIntentReceived(MainScreenIntent.EKeyFieldChange(it))
        },
        placeHolder = {
            CipherText(text = stringResource(R.string.second_e_key_placeholder))
        }
    )
}

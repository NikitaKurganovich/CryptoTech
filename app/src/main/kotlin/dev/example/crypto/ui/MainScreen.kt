package dev.example.crypto.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.example.crypto.R
import dev.example.crypto.ui.components.CipherOutlinedTextField
import dev.example.crypto.ui.components.TextFieldLabel
import dev.example.crypto.ui.config.CipherTheme
import dev.example.crypto.ui.states.base.TextState
import dev.example.crypto.ui.states.implementation.TextStateImpl

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    model: MainScreenViewModel = viewModel()
) {
    val state by model.state.collectAsState()
    ScreenContent(
        modifier = modifier,
        model = model,
        state = state
    )
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    model: MainScreenViewModel = viewModel(),
    state: MainScreenState,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        WorkingArea(
            modifier = Modifier.fillMaxWidth(0.6f),
            model = model,
            state = state
        )
        CipherMethodSelection(
            modifier = Modifier.fillMaxWidth(0.4f),
            onCipherMethodChange = remember { model::onCipherChange }
        )
        Button(
            modifier = Modifier
                .padding(CipherTheme.dimensions.mediumMinus),
            onClick = remember { model::onEncrypt }
        ) {
            Text(
                modifier = Modifier,
                textAlign = TextAlign.Center,
                text = stringResource(R.string.encrypt)
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
private fun CipherMethodSelection(
    modifier: Modifier = Modifier,
    onCipherMethodChange: (Ciphers) -> Unit,
) {
    val options = Ciphers.entries
    var expanded by remember {
        mutableStateOf(false)
    }
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(options.first()) }

    val textState = TextStateImpl(
        label = selectedOption.cipher,
        isError = false
    )
    Column(modifier = modifier) {
        TextFieldLabel(
            modifier = Modifier.clickable {
                expanded = true
            },
            textState = textState
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(CipherTheme.dimensions.smallDefault))
                .padding(horizontal = CipherTheme.dimensions.smallDefault)
        ) {
            options.forEach { cipher ->
                DropdownMenuItem(
                    onClick = {
                        onCipherMethodChange(cipher)
                        onOptionSelected(cipher)
                        expanded = false
                    },
                    text = {
                        Text(
                            text = cipher.cipher,
                            style = CipherTheme.typography.bold.merge()
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun WorkingArea(
    modifier: Modifier = Modifier,
    model: MainScreenViewModel,
    state: MainScreenState,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextFieldLabel(
            modifier = Modifier,
            textState = TextStateImpl(
                label = stringResource(R.string.welcome),
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

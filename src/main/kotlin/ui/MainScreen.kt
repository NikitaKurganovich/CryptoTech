package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.encrypt
import dev.bababnanick.crypto_decoding.generated.resources.welcome
import org.jetbrains.compose.resources.stringResource
import ui.components.CipherOutlinedTextField
import ui.components.TextFieldLabel
import ui.config.CipherTheme
import ui.states.base.TextState
import ui.states.implementation.TextStateImpl

class MainScreen : Screen {
    @Composable
    override fun Content() {
        val model = rememberScreenModel { MainScreenModel() }
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
        model: MainScreenModel,
        state: MainScreenState,
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CipherMethodSelection(
                modifier = Modifier.fillMaxWidth(0.5f)
            )
            WorkingArea(
                modifier = Modifier.fillMaxWidth(0.5f),
                model = model,
                state = state
            )
        }
    }

    @Composable
    private fun CipherMethodSelection(
        modifier: Modifier = Modifier,
    ) {
        Column(
            modifier = modifier
        ) {

        }
    }

    @Composable
    private fun WorkingArea(
        modifier: Modifier = Modifier,
        model: MainScreenModel,
        state: MainScreenState,
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
                onClick = remember {
                    {
                        model.onEncrypt()
                    }
                }
            ) {
                Text(
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    text = stringResource(Res.string.encrypt)
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
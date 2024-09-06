package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.ciphers
import dev.bababnanick.crypto_decoding.generated.resources.encrypt
import dev.bababnanick.crypto_decoding.generated.resources.welcome
import org.jetbrains.compose.resources.stringArrayResource
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
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CipherMethodSelection(
                modifier = Modifier.fillMaxWidth(0.4f)
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
    ) {
        Column(
            modifier = modifier
        ) {
            val options = stringArrayResource(Res.array.ciphers)
            val (selectedOption, onOptionSelected) = remember { mutableStateOf(options.first() ) }

            options.forEach { cipher ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(CipherTheme.dimensions.smallDefault))
                        .selectable(
                            selected = (cipher == selectedOption),
                            onClick = {
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
                        text = cipher,
                        style = CipherTheme.typography.bold.merge(),
                        modifier = Modifier.padding(start = CipherTheme.dimensions.smallDefault)
                    )
                }
            }
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
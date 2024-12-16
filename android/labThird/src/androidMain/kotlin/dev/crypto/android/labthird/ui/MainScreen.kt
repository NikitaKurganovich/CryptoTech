package dev.crypto.android.labthird.ui

import androidkit.components.CipherScreen
import androidkit.kit.CipherCheckbox
import androidkit.kit.CipherText
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.crypto.base.resources.ResultMessage
import dev.crypto.labthird.GenerationOptions
import dev.crypto.labthird.R
import dev.crypto.labthird.ThirdLabIntent
import dev.crypto.labthird.ThirdLabState
import dev.crypto.labthird.ThirdLabString
import dev.crypto.labthird.resources.third_all_chars
import dev.crypto.labthird.resources.third_digits
import dev.crypto.labthird.resources.third_letters
import dev.crypto.labthird.resources.third_special_characters_android
import dev.crypto.ui.kit.CipherButton
import dev.crypto.ui.kit.CipherResultView
import dev.crypto.ui.theme.CipherTheme
import io.github.vinceglb.filekit.compose.rememberFileSaverLauncher
import org.jetbrains.compose.resources.stringResource as cmpStringResource

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = viewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    CipherScreen {
        ScreenContent(
            state = state,
            onIntentReceived = { newIntent -> viewModel.processIntent(newIntent) }
        )
    }
}

@Composable
private fun ScreenContent(
    state: ThirdLabState,
    onIntentReceived: (ThirdLabIntent) -> Unit,
){
    val args by remember(state) {
        derivedStateOf {
            if (state.resultMessage is ResultMessage.IdMessage) {
                (state.resultMessage as ResultMessage.IdMessage).args
            } else emptyArray()
        }
    }
    val launcher = rememberFileSaverLauncher {}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = CipherTheme.dimensions.mediumDefault,
                vertical = CipherTheme.dimensions.largePlus
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CheckboxView(
            state = remember(state) { state },
            onIntent = remember { { onIntentReceived(it) } }
        )
        CipherButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = CipherTheme.dimensions.largeDefault),
            text = stringResource(R.string.third_generate_button)
        ) {
            onIntentReceived(ThirdLabIntent.GeneratePassword{})
        }
        CipherResultView(
            infoText = stringResource(R.string.third_result),
            resultText = state.resultMessage.getString(),
            isError = state.isError
        )
        CipherButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = CipherTheme.dimensions.largeDefault),
            text = stringResource(R.string.third_save_as_file)
        ) {
            if (args.isNotEmpty() && !state.isError) {
                launcher.launch(
                    bytes = args.first().toString().toByteArray(),
                    baseName = "password",
                    extension = "txt"
                )
            }
        }
    }
}

@Composable
private fun CheckboxView(
    modifier: Modifier = Modifier,
    state: ThirdLabState,
    onIntent: (ThirdLabIntent) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        CipherText(
            text = stringResource(R.string.third_password_includes),
            textStyle = CipherTheme.typography.default.copy(
                fontSize = 24.sp
            )
        )
        CheckboxRow(
            modifier = Modifier.padding(vertical = CipherTheme.dimensions.smallDefault),
            checked = state.isLettersSelected,
            onCheckedChange = {
                onIntent(
                    ThirdLabIntent
                        .SetGenerationOption(GenerationOptions.Letters)
                )
            },
            text = cmpStringResource(ThirdLabString.third_letters)
        )
        CheckboxRow(
            modifier = Modifier.padding(vertical = CipherTheme.dimensions.smallDefault),
            checked = state.isDigitsSelected,
            onCheckedChange = {
                onIntent(
                    ThirdLabIntent
                        .SetGenerationOption(GenerationOptions.Digits)
                )
            },
            text = cmpStringResource(ThirdLabString.third_digits)
        )
        CheckboxRow(
            modifier = Modifier.padding(vertical = CipherTheme.dimensions.smallDefault),
            checked = state.isSpecialCharactersSelected,
            onCheckedChange = {
                onIntent(
                    ThirdLabIntent
                        .SetGenerationOption(GenerationOptions.SpecialCharacters)
                )
            },
            text = cmpStringResource(ThirdLabString.third_special_characters_android)
        )
        CheckboxRow(
            modifier = Modifier.padding(vertical = CipherTheme.dimensions.smallDefault),
            checked = state.isAllSelected,
            onCheckedChange = {
                onIntent(ThirdLabIntent.AllOptionsSelected)
            },
            text = cmpStringResource(ThirdLabString.third_all_chars)
        )
    }
}

@Composable
private fun CheckboxRow(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        CipherCheckbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        CipherText(
            modifier = Modifier
                .padding(start = CipherTheme.dimensions.mediumDefault),
            text = text
        )
    }
}
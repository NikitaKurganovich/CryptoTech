package dev.crypto.desktop.thirdlab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import crypto_decoding.desktop.feature.thirdlab.generated.resources.Res
import crypto_decoding.desktop.feature.thirdlab.generated.resources.third_find_chance
import crypto_decoding.desktop.feature.thirdlab.generated.resources.third_password_config
import crypto_decoding.desktop.feature.thirdlab.generated.resources.third_password_length
import crypto_decoding.desktop.feature.thirdlab.generated.resources.third_search_speed
import crypto_decoding.desktop.feature.thirdlab.generated.resources.third_time_to_search
import dev.crypto.labthird.GenerationOptions
import dev.crypto.labthird.ThirdLabIntent
import dev.crypto.labthird.ThirdLabState
import dev.crypto.labthird.ThirdLabString
import dev.crypto.labthird.resources.third_all_chars
import dev.crypto.labthird.resources.third_digits
import dev.crypto.labthird.resources.third_go
import dev.crypto.labthird.resources.third_letters
import dev.crypto.labthird.resources.third_result
import dev.crypto.labthird.resources.third_special_characters_desktop
import dev.crypto.ui.kit.CipherResultView
import io.github.vinceglb.filekit.compose.rememberFileSaverLauncher
import org.jetbrains.compose.resources.stringResource

class ThirdLabScreen : Screen {
    @Composable
    override fun Content() {
        val model = rememberScreenModel { ThirdLabScreenModel() }
        val state by model.state.collectAsState()
        ScreenContent(
            state = state,
            onIntent = model::onNewIntent
        )
    }

    @Composable
    private fun ScreenContent(
        state: ThirdLabState,
        onIntent: (ThirdLabIntent) -> Unit
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PasswordConfig(model = remember(state) { state })
            CharsetSelection(
                model = remember(state) { state },
                onIntent = onIntent
            )
            CipherResultView(
                infoText = stringResource(ThirdLabString.third_result),
                resultText = state.resultMessage.getString(),
                isError = state.isError
            )
        }

    }

    @Composable
    private fun CharsetSelection(
        model: ThirdLabState,
        onIntent: (ThirdLabIntent) -> Unit
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            DefaultCheckbox(
                text = stringResource(ThirdLabString.third_letters),
                isChecked = model.isLettersSelected,
                onCheckChange = {
                    onIntent(ThirdLabIntent.SetGenerationOption(GenerationOptions.Letters))
                }
            )
            DefaultCheckbox(
                text = stringResource(ThirdLabString.third_digits),
                isChecked = model.isDigitsSelected,
                onCheckChange = {
                    onIntent(ThirdLabIntent.SetGenerationOption(GenerationOptions.Digits))
                }
            )
            DefaultCheckbox(
                text = stringResource(ThirdLabString.third_special_characters_desktop),
                isChecked = model.isSpecialCharactersSelected,
                onCheckChange = {
                    onIntent(ThirdLabIntent.SetGenerationOption(GenerationOptions.SpecialCharacters))
                }
            )
            DefaultCheckbox(
                text = stringResource(ThirdLabString.third_all_chars),
                isChecked = model.isAllSelected,
                onCheckChange = {
                    onIntent(ThirdLabIntent.AllOptionsSelected)
                }
            )

            val launcher = rememberFileSaverLauncher {}

            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    onIntent(ThirdLabIntent.GeneratePassword {
                        launcher.launch(
                            bytes = it.toByteArray(),
                            baseName = "password",
                            extension = "txt"
                        )
                    })
                }
            ) {
                Text(stringResource(ThirdLabString.third_go))
            }
        }
    }

    @Composable
    fun DefaultCheckbox(
        modifier: Modifier = Modifier,
        text: String,
        isChecked: Boolean,
        onCheckChange: (Boolean) -> Unit
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckChange
            )
            DefaultText(text)
        }
    }

    @Composable
    private fun PasswordConfig(
        model: ThirdLabState,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleText(stringResource(Res.string.third_password_config))
            with(model.requirements) {
                DefaultText(stringResource(Res.string.third_find_chance, probability))
                DefaultText(stringResource(Res.string.third_search_speed, bruteForceSpeed))
                DefaultText(stringResource(Res.string.third_time_to_search, timeToBruteForce))
            }
            DefaultText(
                stringResource(
                    Res.string.third_password_length,
                    model.passwordLength ?: "?"
                )
            )
        }
    }

    @Composable
    fun TitleText(
        text: String
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 22.sp,
                lineHeight = 28.sp
            )
        )
    }

    @Composable
    private fun DefaultText(
        text: String,
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        )
    }
}
package dev.example.crypto.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.example.crypto.R
import dev.example.crypto.ui.components.CipherOutlinedTextField
import dev.example.crypto.ui.components.CipherText
import dev.example.crypto.ui.components.CipherTextWithBord
import dev.example.crypto.ui.components.EdgeValues
import dev.example.crypto.ui.components.customBorder
import dev.example.crypto.ui.config.CipherTheme
import dev.example.crypto.ui.states.base.TextState
import dev.example.crypto.ui.states.implementation.FieldStateImpl

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    model: MainScreenViewModel = viewModel()
) {
    val state by model.state.collectAsState()
    Box(
        modifier = with(Modifier) {
            fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.bg_crypto),
                    contentScale = ContentScale.FillBounds
                )

        }) {
        ScreenContent(
            modifier = modifier,
            model = model,
            state = state
        )
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    model: MainScreenViewModel = viewModel(),
    state: MainScreenState,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(CipherTheme.dimensions.mediumDefault),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        WorkingArea(
            modifier = Modifier.padding(
                vertical = CipherTheme.dimensions.smallDefault
                    .plus(CipherTheme.dimensions.smallMinus)
            ),
            model = model,
            state = state
        )
        CipherMethodSelection(
            modifier = Modifier,
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

    val textState = FieldStateImpl(
        label = selectedOption.cipher,
        value = selectedOption.cipher,
    )
    val angle by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "")
    val icon = rememberVectorPainter(image = Icons.Default.KeyboardArrowDown)
    CipherTextWithBord(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = true },
        textState = textState,
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .padding(horizontal = CipherTheme.dimensions.mediumDefault)
                    .rotate(angle),
                painter = icon,
                contentDescription = null,
                tint = CipherTheme.colors.iconTint
            )
        }
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(
                horizontal = CipherTheme.dimensions.mediumDefault,
                vertical = CipherTheme.dimensions.smallPlus + CipherTheme.dimensions.smallMinus
            ),
        contentAlignment = Alignment.CenterEnd
    ) {
        DropdownMenu(
            modifier = Modifier
                .padding(horizontal = CipherTheme.dimensions.mediumDefault)
                .fillMaxWidth()
                .customBorder(
                    edgeColor = CipherTheme.colors.borderCorner,
                    innerColor = CipherTheme.colors.border,
                    width = CipherTheme.viewDimensions.borderWidth,
                    edges = EdgeValues(
                        vertical = CipherTheme.viewDimensions.dropdownCornerEdgeHeight,
                        horizontal = CipherTheme.viewDimensions.dropdownCornerEdgeWidth
                    )
                ),
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = Color.Transparent,
        ) {
            options.forEach { cipher ->
                DropdownMenuItem(
                    modifier = Modifier.padding(horizontal = CipherTheme.dimensions.smallDefault),
                    onClick = {
                        onCipherMethodChange(cipher)
                        onOptionSelected(cipher)
                        expanded = false
                    },
                    text = {
                        Text(
                            text = cipher.cipher,
                            style = CipherTheme.typography.default,
                            color = CipherTheme.colors.text
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
        CipherOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = CipherTheme.dimensions.mediumMinus),
            inputFieldState = state.messageInputFieldState,
            onValueChange = { newValue ->
                model.onMessageFieldValueChange(newValue)
            },
            placeHolder = {
                Text(
                    text = state.messageInputFieldState.label,
                    style = CipherTheme.typography.default
                )
            }
        )
        CipherOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = CipherTheme.dimensions.mediumMinus),
            inputFieldState = state.keyInputFieldState,
            onValueChange = { newValue ->
                model.onKeyFieldValueChange(newValue)
            },
            placeHolder = {
                Text(
                    text = state.keyInputFieldState.label,
                    style = CipherTheme.typography.default
                )
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
        CipherText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(CipherTheme.dimensions.smallDefault),
            textState = infoTextState
        )
        CipherText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(CipherTheme.dimensions.smallDefault),
            textState = resultTextState
        )
    }
}

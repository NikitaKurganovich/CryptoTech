package dev.example.crypto.ui

import androidkit.components.CipherScreen
import androidkit.kit.CipherText
import androidkit.kit.CipherTextWithBord
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidkit.kit.EdgeValues
import androidkit.kit.customBorder
import dev.crypto.labfirst.FirstLabIntent
import dev.crypto.labfirst.FirstLabRes
import dev.crypto.labfirst.FirstLabState
import dev.crypto.ui.theme.CipherTheme
import dev.crypto.labfirst.resources.first_encrypt
import dev.crypto.labfirst.resources.first_key_header
import dev.crypto.labfirst.resources.first_key_placeholder
import dev.crypto.labfirst.resources.first_message_placeholder
import dev.crypto.labfirst.resources.first_ok
import dev.crypto.ui.kit.CipherButton
import dev.crypto.ui.kit.CipherOutlinedTextField
import dev.crypto.ui.kit.CipherResultView
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    model: MainScreenViewModel = viewModel()
) {
    val state by model.state.collectAsState()
    CipherScreen {
        ScreenContent(
            modifier = modifier,
            onIntent = { model.onIntent(it) },
            state = remember(state) { state }
        )
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    onIntent: (FirstLabIntent) -> Unit,
    state: FirstLabState,
) {
    val resultMessage = state.resultText.getString()
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
            onIntent = onIntent,
            state = state
        )
        CipherMethodSelection(
            modifier = Modifier,
            onCipherMethodChange = { onIntent(FirstLabIntent.ChangeCipher(it)) }
        )
        CipherButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = CipherTheme.viewDimensions.buttonVerticalPadding),
            text = stringResource(FirstLabRes.first_encrypt),
            onClick = { onIntent(FirstLabIntent.Act) }
        )
        CipherResultView(
            modifier = Modifier
                .padding(bottom = CipherTheme.viewDimensions.resultBottomIndent),
            infoText = remember(state.infoText) { state.infoText },
            resultText = remember(state.resultText) { resultMessage },
            isError = remember(state.isErrorResult) { state.isErrorResult }
        )
    }
}

@Composable
private fun CipherMethodSelection(
    modifier: Modifier = Modifier,
    onCipherMethodChange: (dev.crypto.labfirst.Ciphers) -> Unit,
) {
    val options = dev.crypto.labfirst.Ciphers.entries
    var expanded by remember {
        mutableStateOf(false)
    }
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(options.first()) }

    val textState = selectedOption.cipher
    val angle by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "")
    val icon = rememberVectorPainter(image = Icons.Default.KeyboardArrowDown)
    CipherTextWithBord(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = true },
        text = textState,
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
                    ),
                    background = CipherTheme.colors.dropdownBackground
                ),
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = Color.Transparent
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
    onIntent: (FirstLabIntent) -> Unit,
    state: FirstLabState,
) {
    var expanded by remember { mutableStateOf(false) }
    if (expanded) Dialog(onDismissRequest = { expanded = false }) {
        InfoPopup(
            headerText = stringResource(FirstLabRes.first_key_header),
            contentText = stringResource(state.currentMethod.keyDescription()),
            buttonText = stringResource(FirstLabRes.first_ok),
            onDismiss = { expanded = false }
        )
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CipherOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = CipherTheme.dimensions.mediumMinus),
            value = state.messageInputFieldText,
            onValueChange = {onIntent(FirstLabIntent.MessageFieldChange(it))},
            placeHolder = {
                Text(
                    text = stringResource(FirstLabRes.first_message_placeholder),
                    style = CipherTheme.typography.default
                )
            }
        )
        CipherOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = CipherTheme.dimensions.mediumMinus),
            value = state.keyInputFieldText,
            onValueChange = {onIntent(FirstLabIntent.KeyFieldChange(it))},
            placeHolder = {
                Text(
                    text = stringResource(FirstLabRes.first_key_placeholder),
                    style = CipherTheme.typography.default
                )
            },
            trailIcon = {
                IconButton(
                    modifier = Modifier.padding(horizontal = CipherTheme.dimensions.smallDefault),
                    onClick = { expanded = true }
                ) {
                    Icon(
                        imageVector = Icons.TwoTone.Info,
                        contentDescription = null,
                        tint = CipherTheme.colors.iconTint
                    )
                }
            }
        )
    }
}


@Composable
fun InfoPopup(
    modifier: Modifier = Modifier,
    headerText: String,
    contentText: String,
    buttonText: String,
    onDismiss: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .customBorder(
                edgeColor = CipherTheme.colors.borderCorner,
                innerColor = CipherTheme.colors.border,
                width = CipherTheme.viewDimensions.borderWidth,
                edges = EdgeValues(
                    vertical = CipherTheme.viewDimensions.popupCornerEdgeHeight,
                    horizontal = CipherTheme.viewDimensions.popupCornerEdgeWidth
                ),
                background = CipherTheme.colors.popupBackground
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CipherText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = CipherTheme.dimensions.mediumDefault,
                    horizontal = CipherTheme.dimensions.largeDefault
                ),
            text = headerText,
            textStyle = CipherTheme.typography.default.copy(
                textAlign = TextAlign.Left
            )
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = CipherTheme.dimensions.largeDefault),
            thickness = CipherTheme.dimensions.smallMinus,
            color = CipherTheme.colors.borderCorner
        )
        CipherText(
            modifier = Modifier.padding(
                vertical = CipherTheme.dimensions.mediumDefault,
                horizontal = CipherTheme.dimensions.largeDefault
            ),
            text = contentText,
        )
        Button(
            modifier = Modifier
                .padding(vertical = CipherTheme.dimensions.mediumPlus)
                .size(
                    height = CipherTheme.viewDimensions.popupButtonHeight,
                    width = CipherTheme.viewDimensions.popupButtonWidth
                )
                .border(
                    width = CipherTheme.viewDimensions.popupButtonBorderWidth,
                    color = CipherTheme.colors.border,
                    shape = CutCornerShape(Dp.Hairline)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = CipherTheme.colors.transparentBackground
            ),
            shape = CutCornerShape(Dp.Hairline),
            onClick = onDismiss
        ) {
            CipherText(
                text = buttonText,
                color = CipherTheme.colors.buttonFilled
            )
        }
    }
}
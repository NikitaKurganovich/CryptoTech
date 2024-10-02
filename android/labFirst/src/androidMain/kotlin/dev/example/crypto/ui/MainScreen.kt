package dev.example.crypto.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.example.crypto.R
import androidkit.kit.CipherOutlinedTextField
import androidkit.kit.CipherText
import androidkit.kit.CipherTextWithBord
import androidkit.kit.EdgeValues
import androidkit.kit.customBorder
import androidkit.theme.CipherTheme
import androidkit.states.base.TextState
import androidkit.states.implementation.FieldStateImpl
import androidkit.states.implementation.TextStateImpl

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    model: MainScreenViewModel = viewModel()
) {
    val state by model.state.collectAsState()
    val widthInDp = CipherTheme.viewDimensions.borderWidth
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    var width by remember { mutableStateOf(widthInDp) }
    val animatedWidth by animateDpAsState(
        targetValue = width,
        animationSpec = tween(
            durationMillis = 2000,
            easing = FastOutSlowInEasing
        ),
        label = ""
    )

    LaunchedEffect(Unit) { width = screenWidth }

    Box(
        modifier = with(Modifier) {
            fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.bg_crypto),
                    contentScale = ContentScale.FillBounds
                )

        }) {
        ScreenContent(
            modifier = modifier
                .width(animatedWidth)
                .clipToBounds(),
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
                .fillMaxWidth()
                .padding(vertical = CipherTheme.viewDimensions.buttonVerticalPadding)
                .size(
                    height = CipherTheme.viewDimensions.fieldBaseHeight,
                    width = CipherTheme.viewDimensions.fieldBaseWidth
                ),
            shape = CutCornerShape(Dp.Hairline),
            onClick = remember { model::onEncrypt },
            colors = ButtonDefaults.buttonColors(
                containerColor = CipherTheme.colors.buttonFilled
            )
        ) {
            Text(
                modifier = Modifier,
                textAlign = TextAlign.Center,
                text = stringResource(R.string.encrypt),
                color = CipherTheme.colors.text,
                style = CipherTheme.typography.default
            )
        }
        ResultView(
            modifier = Modifier
                .padding(bottom = CipherTheme.viewDimensions.resultBottomIndent),
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
    model: MainScreenViewModel,
    state: MainScreenState,
) {
    val keyString = state.keyInputFieldState.aboutKey
    val aboutKey by remember(state.keyInputFieldState) { mutableStateOf(keyString) }
    var expanded by remember { mutableStateOf(false) }
    if (expanded) Dialog(onDismissRequest = { expanded = false }) {
        InfoPopup(
            headerText = stringResource(id = R.string.key_header),
            contentText = aboutKey,
            buttonText = stringResource(id = R.string.ok),
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
            },
            trailIcon = {
                IconButton(
                    modifier = Modifier.
                        padding(horizontal = CipherTheme.dimensions.smallDefault),
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
                .padding(bottom = CipherTheme.dimensions.smallPlus),
            textState = infoTextState
        )
        CipherTextWithBord(
            modifier = Modifier
                .fillMaxWidth()
                .size(
                    width = CipherTheme.viewDimensions.resultFieldWidth,
                    height = CipherTheme.viewDimensions.resultFieldHeight
                )
                .padding(top = CipherTheme.dimensions.smallPlus),
            textState = resultTextState
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
            textState = TextStateImpl(
                label = headerText,
                isError = false
            ),
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
            textState = TextStateImpl(
                label = contentText,
                isError = false
            )
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
                textState = TextStateImpl(
                    label = buttonText,
                    isError = false
                ),
                color = CipherTheme.colors.buttonFilled
            )
        }
    }
}
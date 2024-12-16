package dev.crypto.desktop.lab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dev.crypto.desktop.lab.lab.generated.resources.icon
import dev.crypto.desktop.lab.lab.generated.resources.Res
import dev.crypto.desktop.lab.lab.generated.resources.app_name
import dev.crypto.desktop.lab.lab.generated.resources.first_lab
import dev.crypto.desktop.lab.lab.generated.resources.second_lab
import dev.crypto.desktop.lab.lab.generated.resources.third_lab
import dev.crypto.desktop.lab.ui.MainScreen
import dev.crypto.desktop.secondlab.SecondLabScreen
import dev.crypto.desktop.thirdlab.ThirdLabScreen
import dev.crypto.first.FirstLabScreen
import dev.crypto.ui.theme.CipherTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    val state = rememberWindowState(
        size = DpSize(
            width = 1440.dp,
            height = 1024.dp
        )
    )
    Window(
        state = state,
        onCloseRequest = ::exitApplication,
        icon = painterResource(Res.drawable.icon),
        title = stringResource(Res.string.app_name)
    ) {
        CipherTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(CipherTheme.colors.background),
            ) {
                Navigator(
                    MainScreen()
                ) { innerNavigator ->
                    NavigationRow(
                        modifier = Modifier.wrapContentHeight(),
                        navigator = innerNavigator
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .padding(CipherTheme.dimensions.mediumDefault),
                        contentAlignment = Alignment.Center
                    ) {
                        CurrentScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationRow(
    modifier: Modifier = Modifier,
    navigator: Navigator
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(CipherTheme.colors.topRow),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        LabLinkButton(
            modifier = Modifier,
            text = stringResource(Res.string.first_lab),
            navigator = navigator,
            targetScreen = FirstLabScreen()
        )

        LabLinkButton(
            modifier = Modifier,
            text = stringResource(Res.string.second_lab),
            navigator = navigator,
            targetScreen = SecondLabScreen()
        )
        LabLinkButton(
            modifier = Modifier,
            text = stringResource(Res.string.third_lab),
            navigator = navigator,
            targetScreen = ThirdLabScreen()
        )
    }
}

@Composable
fun LabLinkButton(
    modifier: Modifier = Modifier,
    text: String,
    navigator: Navigator,
    targetScreen: Screen
) {
    TextButton(
        modifier = modifier,
        onClick = {
            navigator.replace(item = targetScreen)
        }
    ) {
        Text(text = text)
    }
}
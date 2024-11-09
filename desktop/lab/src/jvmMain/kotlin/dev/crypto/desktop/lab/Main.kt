package dev.crypto.desktop.lab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dev.crypto.desktop.lab.lab.generated.resources.Res
import dev.crypto.desktop.lab.lab.generated.resources.first_lab
import dev.crypto.desktop.lab.lab.generated.resources.second_lab
import dev.crypto.desktop.lab.ui.MainScreen
import dev.crypto.desktop.secondlab.SecondLabScreen
import dev.crypto.first.FirstLabScreen
import dev.crypto.ui.theme.CipherTheme
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Crypto"
    ) {
        CipherTheme {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Navigator(
                    MainScreen()
                ) { innerNavigator ->
                    NavigationRow(
                        modifier = Modifier.height(CipherTheme.dimensions.largeDefault),
                        navigator = innerNavigator
                    )
                    Box(
                        modifier = Modifier.weight(1f).fillMaxSize(),
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
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
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
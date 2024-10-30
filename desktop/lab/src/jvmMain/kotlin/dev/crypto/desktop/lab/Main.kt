package dev.crypto.desktop.lab

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import dev.crypto.desktop.lab.ui.MainScreen
import dev.crypto.ui.theme.CipherTheme

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Crypto"
    ) {
        CipherTheme {
            Navigator(MainScreen())
        }
    }
}


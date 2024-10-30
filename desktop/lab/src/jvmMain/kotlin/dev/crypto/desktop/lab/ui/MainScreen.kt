package dev.crypto.desktop.lab.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import dev.crypto.first.ui.FirstLabScreen

class MainScreen: Screen {
    @Composable
    override fun Content() {
        Navigator(FirstLabScreen())
    }
}
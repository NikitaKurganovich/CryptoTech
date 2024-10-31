package dev.crypto.desktop.lab.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.crypto.desktop.lab.lab.generated.resources.Res
import dev.crypto.desktop.lab.lab.generated.resources.first_lab
import dev.crypto.desktop.lab.lab.generated.resources.second_lab
import dev.crypto.desktop.secondlab.SecondLabScreen
import dev.crypto.first.ui.FirstLabScreen
import org.jetbrains.compose.resources.stringResource

class MainScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        navigator.replace(FirstLabScreen())
    }
}

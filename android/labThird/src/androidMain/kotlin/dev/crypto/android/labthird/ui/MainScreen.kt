package dev.crypto.android.labthird.ui

import androidkit.components.CipherScreen
import androidkit.kit.CipherText
import dev.crypto.ui.theme.CipherTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.crypto.labthird.ThirdLabIntent
import dev.crypto.labthird.ThirdLabState

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
fun ScreenContent(
    state: ThirdLabState,
    onIntentReceived: (ThirdLabIntent) -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = CipherTheme.dimensions.mediumDefault,
                vertical = CipherTheme.dimensions.largePlus
            )
    ) {
        //CipherText(text = stringResource(R.string.third_screen_title))

    }
}

package dev.crypto.android.labsecond.ui

import androidx.compose.runtime.Composable
import androidkit.components.CipherScreen
import androidkit.kit.CipherOutlinedTextField
import androidkit.states.implementation.FieldStateImpl
import androidkit.theme.CipherTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(){
    CipherScreen {
        ScreenContent()
    }
}

@Composable
fun ScreenContent(

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = CipherTheme.dimensions.mediumDefault)
    ) {
        CipherOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = CipherTheme.dimensions.mediumMinus),
            inputFieldState = FieldStateImpl(
                label = "Text",
                value = "Text",
                isError = false
            )
        )
        CipherOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = CipherTheme.dimensions.mediumMinus),
            inputFieldState = FieldStateImpl(
                label = "Text",
                value = "Text",
                isError = false
            )
        )
        CipherOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = CipherTheme.dimensions.mediumMinus),
            inputFieldState = FieldStateImpl(
                label = "Text",
                value = "Text",
                isError = false
            )
        )
        CipherOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = CipherTheme.dimensions.mediumMinus),
            inputFieldState = FieldStateImpl(
                label = "Text",
                value = "Text",
                isError = false
            )
        )
    }
}
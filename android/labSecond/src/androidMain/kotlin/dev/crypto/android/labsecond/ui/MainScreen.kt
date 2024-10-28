package dev.crypto.android.labsecond.ui

import androidx.compose.runtime.Composable
import androidkit.components.CipherScreen
import androidkit.kit.CipherButton
import androidkit.kit.CipherOutlinedTextField
import androidkit.kit.CipherResultView
import androidkit.kit.CipherSwitch
import androidkit.theme.CipherTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
        CipherSwitch(
            modifier = Modifier
                .fillMaxWidth(),
            firstOptionText = "Encrypt",
            secondOptionText = "Decrypt",
        )
        InputFields()
        CipherButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Submit",
            onClick = {}
        )
        CipherResultView(
            modifier = Modifier
                .fillMaxWidth(),
            infoText = "Info",
            resultText = "Result",
            isError = false
        )
    }
}

@Composable
private fun ColumnScope.InputFields() {
    CipherOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = CipherTheme.dimensions.mediumMinus),
        value = "Text",
        onValueChange = {

        }
    )
    CipherOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = CipherTheme.dimensions.mediumMinus),
        value = "Text",
        onValueChange = {

        }
    )
    CipherOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = CipherTheme.dimensions.mediumMinus),
        value = "Text",
        onValueChange = {

        }
    )
    CipherOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = CipherTheme.dimensions.mediumMinus),
        value = "Text",
        onValueChange = {

        }
    )
}
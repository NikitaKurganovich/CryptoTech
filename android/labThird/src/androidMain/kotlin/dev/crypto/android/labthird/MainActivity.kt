package dev.crypto.android.labthird

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.crypto.android.labthird.ui.MainScreen
import dev.crypto.ui.theme.CipherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CipherTheme {
                MainScreen()
            }
        }
    }
}
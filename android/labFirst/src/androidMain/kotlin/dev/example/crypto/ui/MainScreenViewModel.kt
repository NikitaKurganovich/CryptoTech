package dev.example.crypto.ui

import androidx.lifecycle.ViewModel
import dev.crypto.labfirst.FirstLabIntent
import dev.crypto.labfirst.FirstLabIntentHandler

class MainScreenViewModel : ViewModel() {
    private val intentHandler = FirstLabIntentHandler()

    val state = intentHandler.state

    fun onIntent(intent: FirstLabIntent) = intentHandler.processIntent(intent)
}

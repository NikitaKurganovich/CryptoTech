package dev.crypto.android.labthird.ui

import androidx.lifecycle.ViewModel
import dev.crypto.labthird.ThirdLabIntent
import dev.crypto.labthird.ThirdLabIntentHandler
import dev.crypto.labthird.androidPasswordRequirements

class MainScreenViewModel : ViewModel() {
    private val intentHandler = ThirdLabIntentHandler(androidPasswordRequirements)

    val state = intentHandler.state

    fun processIntent(intent: ThirdLabIntent) = intentHandler.processIntent(intent)
}
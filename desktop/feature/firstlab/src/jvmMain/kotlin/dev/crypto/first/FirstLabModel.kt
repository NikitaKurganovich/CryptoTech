package dev.crypto.first

import cafe.adriel.voyager.core.model.ScreenModel
import dev.crypto.labfirst.FirstLabIntent
import dev.crypto.labfirst.FirstLabIntentHandler

class FirstLabModel : ScreenModel {
    private val intentHandler = FirstLabIntentHandler()

    val state = intentHandler.state

    fun onIntent(intent: FirstLabIntent) = intentHandler.processIntent(intent)
}

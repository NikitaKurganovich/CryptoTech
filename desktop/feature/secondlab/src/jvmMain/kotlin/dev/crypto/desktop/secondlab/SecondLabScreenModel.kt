package dev.crypto.desktop.secondlab

import cafe.adriel.voyager.core.model.ScreenModel
import dev.crypto.labsecond.SecondLabIntent
import dev.crypto.labsecond.SecondLabIntentHandler

class SecondLabScreenModel: ScreenModel {
    private val intentHandler = SecondLabIntentHandler()

    val state = intentHandler.state

    fun onNewIntent(intent: SecondLabIntent) = intentHandler.processIntent(intent)
}
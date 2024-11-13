package dev.crypto.desktop.thirdlab

import cafe.adriel.voyager.core.model.ScreenModel
import dev.crypto.labthird.ThirdLabIntent
import dev.crypto.labthird.ThirdLabIntentHandler
import dev.crypto.labthird.desktopPasswordRequirements

class ThirdLabScreenModel: ScreenModel {
    private val intentHandler = ThirdLabIntentHandler(desktopPasswordRequirements)

    val state = intentHandler.state

    fun onNewIntent(intent: ThirdLabIntent) = intentHandler.processIntent(intent)
}
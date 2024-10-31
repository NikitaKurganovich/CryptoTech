package dev.crypto.base.interfaces

import kotlinx.coroutines.flow.StateFlow

interface IntentHandler<State, Intent> {
    val state: StateFlow<State>
    fun processIntent(intent: Intent)
}
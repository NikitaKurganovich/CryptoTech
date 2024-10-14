package dev.example.crypto.domain

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface ResultMessage {
    data class StringMessage(val message: String) : ResultMessage
    data class IdMessage(val id: ErrorId) : ResultMessage

    @Composable
    fun getString() = when (this) {
        is StringMessage -> message
        is IdMessage -> stringResource(id.id)
    }
}
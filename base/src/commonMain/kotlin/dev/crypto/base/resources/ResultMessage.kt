package dev.crypto.base.resources

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource

sealed interface ResultMessage {
    data class StringMessage(val message: String) : ResultMessage
    data class IdMessage(val id: CryptoError, val args: Array<Any> = arrayOf()) : ResultMessage

    @Composable
    fun getString() = when (this) {
        is StringMessage -> message
        is IdMessage -> stringResource(id.errorRes, args)
    }
}
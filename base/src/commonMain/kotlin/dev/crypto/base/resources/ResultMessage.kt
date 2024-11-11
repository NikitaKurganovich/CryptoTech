package dev.crypto.base.resources

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource

sealed interface ResultMessage {
    data class StringMessage(val message: String) : ResultMessage
    data class IdMessage(
        val id: CryptoResId,
        val args: Array<Any> = arrayOf()
    ) : ResultMessage {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as IdMessage

            if (id != other.id) return false
            if (!args.contentEquals(other.args)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = id.hashCode()
            result = 31 * result + args.contentHashCode()
            return result
        }
    }

    @Composable
    fun getString() = when (this) {
        is StringMessage -> message
        is IdMessage -> stringResource(id.errorRes, args)
    }
}
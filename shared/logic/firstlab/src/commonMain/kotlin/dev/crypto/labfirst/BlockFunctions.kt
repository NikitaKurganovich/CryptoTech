package dev.crypto.labfirst

import dev.crypto.base.interfaces.Cipher
import dev.crypto.base.resources.ResultMessage

fun <T> checkMessage(
    vararg additionalChecks: Check<T> = arrayOf(),
    string: String,
    block: () -> T
): T {
    require(!string.contains(Regexes.specialCharacters)) { FirstLabErrors.MessageFormat }
    require(string.isNotEmpty()){ FirstLabErrors.MessageEmpty }

    additionalChecks.forEach {
        if (it.predicate) return it.onFailed()
    }
    return block()
}

fun <T> Cipher<T>.generateMessageResult(
    vararg additionalChecks: Check<ResultMessage> = arrayOf(),
    block: () -> ResultMessage
): Result<ResultMessage> = runCatching {
    checkMessage(
        additionalChecks = additionalChecks,
        string = this.message
    ) { block() }
}

data class Check<T>(
    val predicate: Boolean,
    val onFailed: () -> T
)
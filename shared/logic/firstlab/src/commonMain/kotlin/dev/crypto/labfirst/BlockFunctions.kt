package dev.crypto.labfirst

import dev.crypto.base.interfaces.Cipher
import dev.crypto.labfirst.resources.first_generated_key
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

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
    vararg additionalChecks: Check<String> = arrayOf(),
    block: () -> String
): Result<String> = runCatching {
    checkMessage<String>(
        additionalChecks = additionalChecks,
        string = this.message
    ) { block() }
}

fun <T> Cipher<T>.addGeneratedKey() = runBlocking {
    getString(FirstLabRes.first_generated_key, key.toString())
}

data class Check<T>(
    val predicate: Boolean,
    val onFailed: () -> T
)
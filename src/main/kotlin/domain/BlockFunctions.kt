package domain

import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.empty_warning
import dev.bababnanick.crypto_decoding.generated.resources.format_error
import dev.bababnanick.crypto_decoding.generated.resources.generated
import domain.base.Cipher
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

fun <T> checkMessage(
    vararg additionalChecks: Check<T> = arrayOf(),
    string: String,
    block: () -> T
): T {

    if (string.contains(Regexes.specialCharacters)) runBlocking {
        error(getString(Res.string.format_error))
    }

    if (string.isEmpty()) runBlocking {
        error(getString(Res.string.empty_warning))
    }
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
    getString(Res.string.generated, key.toString())
}

data class Check<T>(
    val predicate: Boolean,
    val onFailed: () -> T
)
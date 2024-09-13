package dev.example.crypto.domain

import dev.example.crypto.domain.Strings.empty_warning
import dev.example.crypto.domain.Strings.format_error
import kotlinx.coroutines.runBlocking

fun <T> runWithChecks(
    vararg additionalCheck: Pair<Boolean, Unit>?,
    message: String,
    block: () -> T
): T = when {
    message.contains(Regexes.specialCharacters) -> runBlocking {
        error(format_error)
    }

    message.isEmpty() -> runBlocking {
        error(empty_warning)
    }

//    additionalCheck.forEach { pair ->
//        pair?.let {
//            it.first ->
//        }
//    }

    else -> block()
}
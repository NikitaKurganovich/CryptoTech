package dev.example.crypto.domain

import dev.example.crypto.R
import dev.example.crypto.domain.Strings.empty_warning
import dev.example.crypto.domain.Strings.format_error
import dev.example.crypto.ui.Ciphers
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

fun findRes(cipher: Ciphers): Int = when (cipher) {
    Ciphers.CESAR -> R.string.about_cesar_key
    Ciphers.SIMPLE_REPLACEMENT -> R.string.about_simple_replacement_key
    Ciphers.VIGENERE -> R.string.about_vigenere_key
    Ciphers.SIMPLE_PERMUTATION -> R.string.about_simple_permutation_key
    Ciphers.COMPLICATED_PERMUTATION -> R.string.about_complicated_permutation_key
}
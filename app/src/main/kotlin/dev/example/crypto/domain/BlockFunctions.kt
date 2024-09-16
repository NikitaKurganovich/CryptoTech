package dev.example.crypto.domain

import dev.example.crypto.R
import dev.example.crypto.domain.Strings.empty_warning
import dev.example.crypto.domain.Strings.format_error
import dev.example.crypto.ui.Ciphers

fun <T> checkMessage(
    vararg additionalCheck: AdditionCheck<T>? = arrayOf(null),
    message: String,
    block: () -> T
): T {
    if (message.contains(Regexes.specialCharacters)) {
        error(format_error)
    }
    if (message.isEmpty()) error(empty_warning)
    additionalCheck.forEach { check->
        check?.let {
            if (it.predicate) {
                return it.action()
            }
        }
    }
    return block.invoke()
}

fun findRes(cipher: Ciphers): Int = when (cipher) {
    Ciphers.CESAR -> R.string.about_cesar_key
    Ciphers.SIMPLE_REPLACEMENT -> R.string.about_simple_replacement_key
    Ciphers.VIGENERE -> R.string.about_vigenere_key
    Ciphers.SIMPLE_PERMUTATION -> R.string.about_simple_permutation_key
    Ciphers.COMPLICATED_PERMUTATION -> R.string.about_complicated_permutation_key
}

data class AdditionCheck<T>(
    val predicate: Boolean,
    val action: () -> T
)
package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.Regexes
import dev.example.crypto.domain.Strings.format_error
import dev.example.crypto.domain.base.CipherKey
import kotlinx.coroutines.runBlocking

class VigenereCipherKey(override val value: String) : CipherKey<String, String> {
    override fun formatKey(): Result<String> = runCatching {
        if (!value.contains(Regexes.specialCharacters) && value.isNotEmpty()) {
            value
        } else {
            runBlocking {
                error(format_error)
            }
        }
    }
}
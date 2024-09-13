package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.Regexes
import dev.example.crypto.domain.Strings.empty_warning
import dev.example.crypto.domain.Strings.format_error
import dev.example.crypto.domain.base.Cipher
import kotlinx.coroutines.runBlocking

class VigenereCipher(
    override val message: String,
    override val key: String,
) : Cipher<String> {
    override fun encrypt(): Result<String> = runCatching {
        when {
            message.contains(Regexes.specialCharacters) -> runBlocking {
                error(format_error)
            }

            message.isEmpty() -> runBlocking {
                error(empty_warning)
            }

            else -> {
                message.mapIndexed { index, char ->
                    val keyChar = key[index % key.length]
                    if (char.isLetter()) {
                        val base = if (char.isUpperCase()) 'A' else 'a'
                        val keyBase = if (keyChar.isUpperCase()) 'A' else 'a'
                        ((char - base + (keyChar - keyBase)) % 26 + base.code).toChar()
                    } else {
                        char
                    }
                }.joinToString("")
            }
        }
    }
}
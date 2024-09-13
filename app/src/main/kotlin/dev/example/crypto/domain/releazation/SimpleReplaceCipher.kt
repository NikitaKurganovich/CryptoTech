package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.Strings.format_error
import dev.example.crypto.domain.base.Cipher
import dev.example.crypto.domain.Regexes
import dev.example.crypto.domain.Strings.empty_warning
import kotlinx.coroutines.runBlocking

class SimpleReplaceCipher(
    override val message: String,
    override val key: String,
) : Cipher<String> {
    private val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    override fun encrypt(): Result<String> = runCatching {
        when {
            message.contains(Regexes.specialCharacters) -> runBlocking {
                error(format_error)
            }

            message.isEmpty() -> runBlocking {
                error(empty_warning)
            }

            else -> {
                message.map { char ->
                    val isLowerCase = char.isLowerCase()
                    val index = alphabet.indexOf(char.uppercaseChar())
                    if (index != -1) {
                        val encryptedChar = key[index]
                        if (isLowerCase) encryptedChar.lowercaseChar() else encryptedChar
                    } else {
                        char
                    }
                }.joinToString("")
            }
        }
    }
}
package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.Regexes.specialCharacters
import dev.example.crypto.domain.Strings.empty_warning
import dev.example.crypto.domain.Strings.format_error
import dev.example.crypto.domain.base.Cipher
import kotlinx.coroutines.runBlocking

class CesarCipher(
    override val message: String,
    override val key: Int,
) : Cipher<Int> {
    override fun encrypt(): Result<String> = runCatching {
        when {
            message.contains(specialCharacters) -> runBlocking {
                error(format_error)
            }

            message.isEmpty() -> runBlocking {
                error(empty_warning)
            }

            else -> {
                message.map {
                    Char(
                        (it.code + key - 'a'.code).mod(26) + 'a'.code
                    )
                }.joinToString("")
            }
        }
    }
}
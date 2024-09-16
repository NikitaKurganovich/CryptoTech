package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.base.Cipher
import dev.example.crypto.domain.checkMessage

class CesarCipher(
    override val message: String,
    override val key: Int,
) : Cipher<Int> {
    override fun encrypt(): Result<String> = runCatching {
        checkMessage(
            message = message,
            block = {
                message.map {
                    Char(
                        (it.code + key - 'a'.code).mod(26) + 'a'.code
                    )
                }.joinToString("")
            }
        )
    }
}
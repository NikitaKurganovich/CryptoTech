package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.base.Cipher
import dev.example.crypto.domain.checkMessage

class VigenereCipher(
    override val message: String,
    override val key: String,
) : Cipher<String> {
    override fun encrypt(): Result<String> = runCatching {
        checkMessage(
            message = message,
            block = {
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
        )
    }
}
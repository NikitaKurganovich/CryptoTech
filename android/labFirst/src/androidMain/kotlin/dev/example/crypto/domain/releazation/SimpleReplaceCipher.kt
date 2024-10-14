package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.base.Cipher
import dev.example.crypto.domain.checkMessage

class SimpleReplaceCipher(
    override val message: String,
    override val key: String,
) : Cipher<String> {
    private val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    override fun encrypt(): Result<String> = runCatching {
        checkMessage(
            message = message,
        ) {
            message.map { char ->
                val isLowerCase = char.isLowerCase()
                val index = alphabet.indexOf(char.uppercaseChar())
                if (index != -1) {
                    val encryptedChar = key[index]
                    if (isLowerCase) encryptedChar.lowercaseChar() else encryptedChar
                } else {
                    char
                }
            }.joinToString("") + " ( $key )"
        }
    }
}
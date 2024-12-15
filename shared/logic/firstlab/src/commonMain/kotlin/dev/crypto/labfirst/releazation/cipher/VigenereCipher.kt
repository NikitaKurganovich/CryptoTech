package dev.crypto.labfirst.releazation.cipher

import dev.crypto.base.interfaces.Cipher
import dev.crypto.base.resources.ResultMessage
import dev.crypto.labfirst.checkMessage
import dev.crypto.labfirst.generateMessageResult

class VigenereCipher(
    override val message: String,
    override val key: String,
) : Cipher<String> {
    override fun encrypt(): Result<ResultMessage> = generateMessageResult {
        ResultMessage.StringMessage(
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
        )
    }

    override fun decrypt(): Result<ResultMessage> = runCatching {
        ResultMessage.StringMessage(
            checkMessage(string = message) {
                message.mapIndexed { index, char ->
                    Char(
                        (char.code - key[index % key.length].code).mod(26) + 'a'.code
                    )
                }.joinToString("")
            }
        )
    }
}
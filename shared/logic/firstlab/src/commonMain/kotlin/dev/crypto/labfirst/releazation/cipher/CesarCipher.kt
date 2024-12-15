package dev.crypto.labfirst.releazation.cipher

import dev.crypto.base.interfaces.Cipher
import dev.crypto.base.resources.ResultMessage
import dev.crypto.labfirst.checkMessage

class CesarCipher(
    override val message: String,
    override val key: Int,
) : Cipher<Int> {
    override fun encrypt(): Result<ResultMessage> = runCatching {
        checkMessage(string = message) {
            ResultMessage.StringMessage(
                message.map {
                    Char(
                        (it.code + key - 'a'.code).mod(26) + 'a'.code
                    )
                }.joinToString("")
            )
        }
    }

    override fun decrypt(): Result<ResultMessage> = runCatching {
        checkMessage(string = message) {
            ResultMessage.StringMessage(
                message.map {
                    Char(
                        (it.code - key - 'a'.code).mod(26) + 'a'.code
                    )
                }.joinToString("")
            )
        }
    }
}
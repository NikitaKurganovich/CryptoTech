package dev.crypto.labfirst.releazation.cipher

import dev.crypto.base.interfaces.Cipher
import dev.crypto.base.resources.ResultMessage
import dev.crypto.labfirst.FirstLabResults
import dev.crypto.labfirst.checkMessage

class SimpleReplaceCipher(
    override val message: String,
    override val key: String,
) : Cipher<String> {
    private val alphabet = ('A'..'Z').toList()

    override fun encrypt(): Result<ResultMessage> = runCatching {
        checkMessage(
            string = message,
        ) {
            ResultMessage.IdMessage(
                FirstLabResults.CryptoWithGeneratedKey,
                arrayOf(message.map { char ->
                    val isLowerCase = char.isLowerCase()
                    val index = alphabet.indexOf(char.uppercaseChar())
                    if (index != -1) {
                        val encryptedChar = key[index]
                        if (isLowerCase) encryptedChar.lowercaseChar() else encryptedChar
                    } else char
                }.joinToString(""), key)
            )
        }
    }

    override fun decrypt(): Result<ResultMessage> = runCatching {
        checkMessage(string = message) {
            ResultMessage.IdMessage(
                FirstLabResults.CryptoWithGeneratedKey,
                arrayOf(
                    message.map { char ->
                        val isLowerCase = char.isLowerCase()
                        val index = key.indexOf(char.lowercaseChar())
                        if (index != -1) {
                            val decryptedChar = alphabet[index]
                            if (isLowerCase) decryptedChar.lowercaseChar() else decryptedChar
                        } else char
                    }.joinToString(""), key
                )
            )
        }
    }
}
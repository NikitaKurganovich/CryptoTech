package dev.crypto.first.domain.releazation.cipher

import dev.crypto.base.interfaces.Cipher
import dev.crypto.first.domain.addGeneratedKey
import dev.crypto.first.domain.checkMessage

class SimpleReplaceCipher(
    override val message: String,
    override val key: String,
) : Cipher<String> {
    private val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    override fun encrypt(): Result<String> = runCatching {
        checkMessage(
            string = message,
        ){
            message.map { char ->
                val isLowerCase = char.isLowerCase()
                val index = alphabet.indexOf(char.uppercaseChar())
                if (index != -1) {
                    val encryptedChar = key[index]
                    if (isLowerCase) encryptedChar.lowercaseChar() else encryptedChar
                } else char
            }.joinToString("") + this.addGeneratedKey()
        }
    }



override fun decrypt(): Result<String> = runCatching {
    checkMessage(string = message) {
        message.map { char ->
            val isLowerCase = char.isLowerCase()
            val index = key.indexOf(char.lowercaseChar())
            if (index != -1) {
                val encryptedChar = alphabet[index]
                if (isLowerCase) encryptedChar.lowercaseChar() else encryptedChar
            } else char
        }.joinToString("") + this.addGeneratedKey()
    }
}

}
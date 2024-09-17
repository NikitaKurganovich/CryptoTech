package domain.releazation.cipher

import domain.base.Cipher
import domain.checkMessage

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
                } else {
                    char
                }
            }.joinToString("")
        }
    }



override fun decrypt(): Result<String> = runCatching {
    checkMessage(string = message) {
        message.map {
            Char(
                alphabet.indexOf(it) - key.toInt()
            )
        }.joinToString("")
    }
}

}
package firstlab.domain.releazation.cipher

import firstlab.domain.addGeneratedKey
import firstlab.domain.base.Cipher
import firstlab.domain.checkMessage
import firstlab.domain.generateMessageResult

class VigenereCipher(
    override val message: String,
    override val key: String,
) : Cipher<String> {
    override fun encrypt(): Result<String> = generateMessageResult {
        message.mapIndexed { index, char ->
            val keyChar = key[index % key.length]
            if (char.isLetter()) {
                val base = if (char.isUpperCase()) 'A' else 'a'
                val keyBase = if (keyChar.isUpperCase()) 'A' else 'a'
                ((char - base + (keyChar - keyBase)) % 26 + base.code).toChar()
            } else {
                char
            }
        }.joinToString("") + this.addGeneratedKey()
    }



override fun decrypt(): Result<String> = runCatching {
    checkMessage(string = message) {
        message.mapIndexed { index, char ->
            Char(
                (char.code - key[index % key.length].code + 'a'.code).mod(26) + 'a'.code
            )
        }.joinToString("")
    }
}

}
package firstlab.domain.releazation.cipher

import firstlab.domain.base.Cipher
import firstlab.domain.checkMessage

class CesarCipher(
    override val message: String,
    override val key: Int,
) : Cipher<Int> {
    override fun encrypt(): Result<String> = runCatching {
        checkMessage(string = message) {
            message.map {
                Char(
                    (it.code + key - 'a'.code).mod(26) + 'a'.code
                )
            }.joinToString("")
        }
    }

    override fun decrypt(): Result<String> = runCatching {
        checkMessage(string = message) {
            message.map {
                Char(
                    (it.code - key - 'a'.code).mod(26) + 'a'.code
                )
            }.joinToString("")
        }
    }
}
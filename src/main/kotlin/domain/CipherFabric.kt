package domain

import domain.base.Encryptable
import domain.releazation.CesarCipher
import domain.releazation.StringIntCipherKey
import ui.Ciphers

class CipherFabric(
    private val cipher: Ciphers,
    private val key: String,
    private val message: String,
) {
    fun createCipher(): Result<Encryptable> = runCatching {
        when (cipher) {
            Ciphers.CESAR -> CesarCipher(
                message = message,
                key = StringIntCipherKey(key).formatKey().getOrThrow()
            )
            Ciphers.SIMPLE_REPLACEMENT -> CesarCipher(message, key.toInt())
            //SimpleReplacementCipher(message, key)
            Ciphers.VIGENERE -> CesarCipher(message, key.toInt())
            //VigenereCipher(message, key)
            Ciphers.SIMPLE_PERMUTATION -> CesarCipher(message, key.toInt())
            //SimplePermutationCipher(message, key)
            Ciphers.COMPLICATED_PERMUTATION -> CesarCipher(message, key.toInt())
            //ComplicatedPermutationCipher(message, key)
        }
    }
}
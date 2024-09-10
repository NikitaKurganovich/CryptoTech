package domain

import domain.base.Encryptable
import domain.releazation.*
import ui.Ciphers

class CipherFabric(
    private val cipher: Ciphers,
    private val key: String,
    private val message: String,
) {
    fun createCipher(): Result<Encryptable> = runCatching {
        when (cipher) {
            Ciphers.CESAR -> CesarCipher(
                message = message, key = StringIntCipherKey(key).formatKey().getOrThrow()
            )

            Ciphers.SIMPLE_REPLACEMENT -> SimpleReplaceCipher(
                message = message, key = AlphabetCipherKey(key).formatKey().getOrThrow()
            )

            Ciphers.VIGENERE -> VigenereCipher(
                message = message, key = VigenereCipherKey(key).formatKey().getOrThrow()
            )

            Ciphers.SIMPLE_PERMUTATION -> SimplePermutationCipher(
                message = message,
                key = SimplePermutationCipherKey(key).formatKey().getOrThrow()
            )
            //SimplePermutationCipher(message, key)
            Ciphers.COMPLICATED_PERMUTATION -> ComplicatedPermutationCipher(
                message = message,
                key = ComplicatedPermutationCipherKey(key).formatKey().getOrThrow()
            )
            //ComplicatedPermutationCipher(message, key)
        }
    }
}
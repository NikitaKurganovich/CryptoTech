package firstlab.domain

import firstlab.domain.base.Encryptable
import firstlab.domain.releazation.cipher.CesarCipher
import firstlab.domain.releazation.cipher.ComplicatedPermutationCipher
import firstlab.domain.releazation.cipher.SimplePermutationCipher
import firstlab.domain.releazation.cipher.SimpleReplaceCipher
import firstlab.domain.releazation.cipher.VigenereCipher
import firstlab.domain.releazation.key.AlphabetCipherKey
import firstlab.domain.releazation.key.ComplicatedPermutationCipherKey
import firstlab.domain.releazation.key.SimplePermutationCipherKey
import firstlab.domain.releazation.key.StringIntCipherKey
import firstlab.domain.releazation.key.VigenereCipherKey
import firstlab.ui.Ciphers

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

            Ciphers.COMPLICATED_PERMUTATION -> ComplicatedPermutationCipher(
                message = message,
                key = ComplicatedPermutationCipherKey(key).formatKey().getOrThrow()
            )
        }
    }
}
package dev.crypto.labfirst

import dev.crypto.base.interfaces.Encryptable
import dev.crypto.labfirst.releazation.cipher.CesarCipher
import dev.crypto.labfirst.releazation.cipher.ComplicatedPermutationCipher
import dev.crypto.labfirst.releazation.cipher.SimplePermutationCipher
import dev.crypto.labfirst.releazation.cipher.SimpleReplaceCipher
import dev.crypto.labfirst.releazation.cipher.VigenereCipher
import dev.crypto.labfirst.releazation.key.AlphabetCipherKey
import dev.crypto.labfirst.releazation.key.ComplicatedPermutationCipherKey
import dev.crypto.labfirst.releazation.key.SimplePermutationCipherKey
import dev.crypto.labfirst.releazation.key.StringIntCipherKey
import dev.crypto.labfirst.releazation.key.VigenereCipherKey


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
package dev.crypto.first.domain

import dev.crypto.base.interfaces.Encryptable
import dev.crypto.first.domain.releazation.cipher.CesarCipher
import dev.crypto.first.domain.releazation.cipher.ComplicatedPermutationCipher
import dev.crypto.first.domain.releazation.cipher.SimplePermutationCipher
import dev.crypto.first.domain.releazation.cipher.SimpleReplaceCipher
import dev.crypto.first.domain.releazation.cipher.VigenereCipher
import dev.crypto.first.domain.releazation.key.AlphabetCipherKey
import dev.crypto.first.domain.releazation.key.ComplicatedPermutationCipherKey
import dev.crypto.first.domain.releazation.key.SimplePermutationCipherKey
import dev.crypto.first.domain.releazation.key.StringIntCipherKey
import dev.crypto.first.domain.releazation.key.VigenereCipherKey
import dev.crypto.first.ui.Ciphers


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
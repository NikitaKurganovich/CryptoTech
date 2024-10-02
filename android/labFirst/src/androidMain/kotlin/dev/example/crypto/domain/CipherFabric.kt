package dev.example.crypto.domain

import dev.example.crypto.domain.base.Encryptable
import dev.example.crypto.domain.releazation.AlphabetCipherKey
import dev.example.crypto.domain.releazation.CesarCipher
import dev.example.crypto.domain.releazation.ComplicatedPermutationCipher
import dev.example.crypto.domain.releazation.ComplicatedPermutationCipherKey
import dev.example.crypto.domain.releazation.SimplePermutationCipher
import dev.example.crypto.domain.releazation.SimplePermutationCipherKey
import dev.example.crypto.domain.releazation.SimpleReplaceCipher
import dev.example.crypto.domain.releazation.StringIntCipherKey
import dev.example.crypto.domain.releazation.VigenereCipher
import dev.example.crypto.domain.releazation.VigenereCipherKey
import dev.example.crypto.ui.Ciphers

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
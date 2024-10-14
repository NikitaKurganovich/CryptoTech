package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.AdditionCheck
import dev.example.crypto.domain.InputErrors
import dev.example.crypto.domain.base.Cipher
import dev.example.crypto.domain.checkMessage

class SimplePermutationCipher(
    override val message: String,
    override val key: List<Int>,
) : Cipher<List<Int>> {
    override fun encrypt(): Result<String> = runCatching {
        checkMessage(
            AdditionCheck(
                message.length % key.size != 0
            ) { error(InputErrors.KeyLength) },
            message = message,
            block = {
                val chunkSize = key.size
                val chunks = message.chunked(chunkSize)
                val encryptedChunks = chunks.map { chunk ->
                    key.map { chunk.getOrNull(it - 1) ?: '_' }.joinToString("")
                }
                encryptedChunks.joinToString("")
            }
        )
    }
}
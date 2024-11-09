package dev.crypto.labfirst.releazation.cipher

import dev.crypto.base.interfaces.Cipher
import dev.crypto.labfirst.Check
import dev.crypto.labfirst.FirstLabRes
import dev.crypto.labfirst.addGeneratedKey
import dev.crypto.labfirst.checkMessage
import dev.crypto.labfirst.generateMessageResult
import dev.crypto.labfirst.resources.first_key_format_warning

class SimplePermutationCipher(
    override val message: String,
    override val key: List<Int>,
) : Cipher<List<Int>> {
    override fun encrypt(): Result<String> = runCatching {
        checkMessage(
            Check(message.length % key.size != 0) {
                error(FirstLabRes.first_key_format_warning)
            },
            string = message,
        ) {
            val chunkSize = key.size
            val chunks = message.chunked(chunkSize)
            val encryptedChunks = chunks.map { chunk ->
                key.map { chunk.getOrNull(it - 1) ?: chunk[it] }.joinToString("")
            }
            encryptedChunks.joinToString("") + this.addGeneratedKey()
        }
    }

    override fun decrypt(): Result<String> = generateMessageResult(
        Check(message.length % key.size != 0) {
            error(FirstLabRes.first_key_format_warning)
        }
    ) {
        val chunkSize = key.size
        val chunks = message.chunked(chunkSize)
        val encryptedChunks = chunks.map { chunk ->
            key.map {
                chunk.getOrNull(it) ?: chunk[it - 1]
            }.joinToString("")
        }
        encryptedChunks.joinToString("") + this.addGeneratedKey()
    }
}
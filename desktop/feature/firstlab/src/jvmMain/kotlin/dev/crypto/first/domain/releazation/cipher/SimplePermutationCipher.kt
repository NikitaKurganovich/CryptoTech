package dev.crypto.first.domain.releazation.cipher

import crypto_decoding.desktop.feature.firstlab.generated.resources.Res
import crypto_decoding.desktop.feature.firstlab.generated.resources.key_warning
import dev.crypto.base.interfaces.Cipher
import dev.crypto.first.domain.Check
import dev.crypto.first.domain.addGeneratedKey
import dev.crypto.first.domain.checkMessage
import dev.crypto.first.domain.generateMessageResult
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

class SimplePermutationCipher(
    override val message: String,
    override val key: List<Int>,
) : Cipher<List<Int>> {
    override fun encrypt(): Result<String> = runCatching {
        checkMessage(
            Check(message.length % key.size != 0) {
                runBlocking {
                    error(getString(Res.string.key_warning))
                }
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
            runBlocking {
                error(getString(Res.string.key_warning))
            }
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
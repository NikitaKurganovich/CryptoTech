package domain.releazation.cipher

import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.key_warning
import domain.Check
import domain.addGeneratedKey
import domain.base.Cipher
import domain.checkMessage
import domain.generateMessageResult
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
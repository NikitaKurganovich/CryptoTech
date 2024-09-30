package firstlab.domain.releazation.cipher

import firstlab.labfirst.generated.resources.Res
import firstlab.labfirst.generated.resources.key_warning
import firstlab.domain.Check
import firstlab.domain.addGeneratedKey
import firstlab.domain.base.Cipher
import firstlab.domain.checkMessage
import firstlab.domain.generateMessageResult
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
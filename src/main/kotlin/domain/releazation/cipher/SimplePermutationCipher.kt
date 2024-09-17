package domain.releazation.cipher

import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.key_warning
import domain.Check
import domain.base.Cipher
import domain.checkMessage
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
                key.map { chunk.getOrNull(it - 1) ?: '_' }.joinToString("")
            }
            encryptedChunks.joinToString("")
        }
    }

    override fun decrypt(): Result<String> = runCatching{
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
                key.map { chunk.getOrNull(it + 1) ?: '_' }.joinToString("")
            }
            encryptedChunks.joinToString("")
        }

    }
}
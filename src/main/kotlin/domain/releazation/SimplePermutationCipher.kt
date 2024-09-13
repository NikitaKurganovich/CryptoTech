package domain.releazation

import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.empty_warning
import dev.bababnanick.crypto_decoding.generated.resources.format_error
import dev.bababnanick.crypto_decoding.generated.resources.key_length_warning
import domain.Regexes
import domain.base.Cipher
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

class SimplePermutationCipher(
    override val message: String,
    override val key: List<Int>,
) : Cipher<List<Int>> {
    override fun encrypt(): Result<String> = runCatching {
        when {
            message.contains(Regexes.specialCharacters) -> runBlocking {
                error(getString(Res.string.format_error))
            }

            message.isEmpty() -> runBlocking {
                error(getString(Res.string.empty_warning))
            }

            message.length % key.size != 0 -> {
                runBlocking {
                    error(getString(Res.string.key_length_warning))
                }
            }

            else -> {
                val chunkSize = key.size
                val chunks = message.chunked(chunkSize)
                val encryptedChunks = chunks.map { chunk ->
                    key.map { chunk.getOrNull(it - 1) ?: '_' }.joinToString("")
                }
                encryptedChunks.joinToString("")
            }
        }
    }
}
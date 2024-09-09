package domain.releazation

import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.format_error
import domain.Regexes
import domain.base.Cipher
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

class SimplePermutationCipher(
    override val message: String,
    override val key: List<Int>,
) : Cipher<List<Int>> {
    override fun encrypt(): Result<String> = runCatching {
        if (!message.contains(Regexes.specialCharacters)) {
            val chunkSize = key.size
            val chunks = message.chunked(chunkSize)
            val encryptedChunks = chunks.map { chunk ->
                key.map { chunk.getOrNull(it - 1) ?: '_' }.joinToString("")
            }
            encryptedChunks.joinToString("")
        } else {
            runBlocking {
                error(getString(Res.string.format_error))
            }
        }
    }
}
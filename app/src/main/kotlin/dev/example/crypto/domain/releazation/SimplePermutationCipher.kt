package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.base.Cipher
import dev.example.crypto.domain.Regexes
import dev.example.crypto.domain.Strings
import dev.example.crypto.domain.Strings.empty_warning
import dev.example.crypto.domain.Strings.key_length_warning
import kotlinx.coroutines.runBlocking

class SimplePermutationCipher(
    override val message: String,
    override val key: List<Int>,
) : Cipher<List<Int>> {
    override fun encrypt(): Result<String> = runCatching {
        when {
            message.contains(Regexes.specialCharacters) -> runBlocking {
                error(Strings.format_error)
            }

            message.isEmpty() -> runBlocking {
                error(empty_warning)
            }

            message.length % key.size != 0 -> {
                runBlocking {
                    error(key_length_warning)
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
package dev.crypto.labfirst.releazation.cipher

import dev.crypto.base.interfaces.Cipher
import dev.crypto.base.resources.ResultMessage
import dev.crypto.labfirst.Check
import dev.crypto.labfirst.FirstLabErrors
import dev.crypto.labfirst.FirstLabResults
import dev.crypto.labfirst.checkMessage
import dev.crypto.labfirst.generateMessageResult

class SimplePermutationCipher(
    override val message: String,
    override val key: List<Int>,
) : Cipher<List<Int>> {
    override fun encrypt(): Result<ResultMessage> = runCatching {
        checkMessage(
            Check(message.length % key.size != 0) {
                error(FirstLabErrors.KeyLength)
            },
            string = message,
        ) {
            val chunkSize = key.size
            val chunks = message.chunked(chunkSize)
            val encryptedChunks = chunks.joinToString("") { chunk ->
                key.joinToString("") {
                    (chunk.getOrNull(it - 1) ?: chunk[it]).toString()
                }
            }

            ResultMessage.IdMessage(
                FirstLabResults.CryptoWithGeneratedKey,
                arrayOf(encryptedChunks, key.toString())
            )
        }
    }

    override fun decrypt(): Result<ResultMessage> = generateMessageResult(
        Check(message.length % key.size != 0) {
            error(FirstLabErrors.KeyLength)
        }
    ) {
        val chunkSize = key.size
        val chunks = message.chunked(chunkSize)
        val encryptedChunks = chunks.joinToString("") { chunk ->
            key.associateWith {
                chunk[key.indexOf(it)]
            }.toSortedMap().values.joinToString("")
        }
        ResultMessage.IdMessage(
            FirstLabResults.CryptoWithGeneratedKey,
            arrayOf(encryptedChunks, key.toString())
        )
    }
}
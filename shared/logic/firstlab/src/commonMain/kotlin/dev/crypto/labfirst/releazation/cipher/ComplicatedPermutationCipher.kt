package dev.crypto.labfirst.releazation.cipher

import dev.crypto.base.interfaces.Cipher
import dev.crypto.base.resources.ResultMessage
import dev.crypto.labfirst.Check
import dev.crypto.labfirst.FirstLabErrors
import dev.crypto.labfirst.FirstLabResults
import dev.crypto.labfirst.checkMessage
import dev.crypto.labfirst.generateMessageResult

class ComplicatedPermutationCipher(
    override val message: String,
    override val key: Pair<List<Int>, List<Int>>,
) : Cipher<Pair<List<Int>, List<Int>>> {
    override fun encrypt(): Result<ResultMessage> = generateMessageResult(
        Check(key.first.size * key.second.size != message.length) {
            error(FirstLabErrors.KeyMultiplication)
        }
    ) {
        val (key1, key2) = key
        val groupSize = key1.size
        val groups = message.chunked(groupSize)
        val transposedGroups = groups.map { group ->
            val transposedGroup = CharArray(groupSize)
            key1.forEachIndexed { index, keyIndex ->
                if (keyIndex - 1 < group.length) {
                    transposedGroup[index] = group[keyIndex - 1]
                }
            }
            String(transposedGroup)
        }

        val reorderedGroups = key2.map { keyIndex ->
            if (keyIndex - 1 < transposedGroups.size) {
                transposedGroups[keyIndex - 1]
            } else {
                ""
            }
        }

        ResultMessage.IdMessage(FirstLabResults.CryptoWithGeneratedKey, arrayOf(reorderedGroups.joinToString(""), key.toString()))
    }

    override fun decrypt(): Result<ResultMessage> = runCatching {
        checkMessage(
            Check(key.first.size * key.second.size != message.length) {
                error(FirstLabErrors.KeyMultiplication)
            },
            string = message,
        ) {
            val (key1, key2) = key
            val groupSize = key1.size
            val groups = message.chunked(groupSize)
            val reorderedGroups = key2.map { keyIndex ->
                if (keyIndex - 1 < groups.size) {
                    groups[keyIndex - 1]
                } else {
                    ""
                }
            }

            val transposedGroups = reorderedGroups.map { group ->
                val transposedGroup = CharArray(groupSize)
                key1.forEachIndexed { index, keyIndex ->
                    if (keyIndex - 1 < group.length) {
                        transposedGroup[keyIndex - 1] = group[index]
                    }
                }
                String(transposedGroup)
            }

            ResultMessage.IdMessage(FirstLabResults.CryptoWithGeneratedKey, arrayOf(transposedGroups.joinToString(""), key.toString()))
        }
    }
}
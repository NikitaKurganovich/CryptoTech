package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.AdditionCheck
import dev.example.crypto.domain.InputErrors
import dev.example.crypto.domain.base.Cipher
import dev.example.crypto.domain.checkMessage

class ComplicatedPermutationCipher(
    override val message: String,
    override val key: Pair<List<Int>, List<Int>>,
) : Cipher<Pair<List<Int>, List<Int>>> {
    override fun encrypt(): Result<String> = runCatching {
        checkMessage(
            AdditionCheck(
                key.first.size * key.second.size != message.length
            ) { error(InputErrors.KeyLength) },
            message = message,
            block = {
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

                reorderedGroups.joinToString("")
            }
        )
    }
}
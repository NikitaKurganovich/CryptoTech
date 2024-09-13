package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.Regexes
import dev.example.crypto.domain.Strings.empty_warning
import dev.example.crypto.domain.Strings.format_error
import dev.example.crypto.domain.Strings.key_length_warning
import dev.example.crypto.domain.base.Cipher
import kotlinx.coroutines.runBlocking

class ComplicatedPermutationCipher(
    override val message: String,
    override val key: Pair<List<Int>, List<Int>>,
) : Cipher<Pair<List<Int>, List<Int>>> {
    override fun encrypt(): Result<String> = runCatching {
        when {
            message.contains(Regexes.specialCharacters) -> runBlocking {
                error(format_error)
            }

            message.isEmpty() -> runBlocking {
                error(empty_warning)
            }

            key.first.size * key.second.size != message.length -> {
                runBlocking {
                    error(key_length_warning)
                }
            }

            else -> {
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
        }
    }
}
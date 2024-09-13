package domain.releazation

import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.empty_warning
import dev.bababnanick.crypto_decoding.generated.resources.format_error
import dev.bababnanick.crypto_decoding.generated.resources.key_length_warning
import domain.Regexes
import domain.base.Cipher
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

class ComplicatedPermutationCipher(
    override val message: String,
    override val key: Pair<List<Int>, List<Int>>,
) : Cipher<Pair<List<Int>, List<Int>>> {
    override fun encrypt(): Result<String> = runCatching {
        when {
            message.contains(Regexes.specialCharacters) -> runBlocking {
                error(getString(Res.string.format_error))
            }

            message.isEmpty() -> runBlocking {
                error(getString(Res.string.empty_warning))
            }

            key.first.size * key.second.size != message.length -> {
                runBlocking {
                    error(getString(Res.string.key_length_warning))
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
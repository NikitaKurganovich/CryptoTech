package domain.releazation.cipher

import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.key_warning
import domain.Check
import domain.base.Cipher
import domain.checkMessage
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

class ComplicatedPermutationCipher(
    override val message: String,
    override val key: Pair<List<Int>, List<Int>>,
) : Cipher<Pair<List<Int>, List<Int>>> {
    override fun encrypt(): Result<String> = runCatching {
        checkMessage(
            Check(key.first.size * key.second.size != message.length){
                runBlocking {
                    error(getString(Res.string.key_warning))
                }
            },
            string = message,
        ){
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



override fun decrypt(): Result<String> = runCatching {
    checkMessage(
        Check(key.first.size * key.second.size != message.length){
            runBlocking {
                error(getString(Res.string.key_warning))
            }
        },
        string = message,
    ){
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

        transposedGroups.joinToString("")
    }
}

}
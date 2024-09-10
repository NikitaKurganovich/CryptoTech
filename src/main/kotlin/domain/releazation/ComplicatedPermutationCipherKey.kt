package domain.releazation

import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.numbers_warning
import domain.base.CipherKey
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString
import kotlin.random.Random

class ComplicatedPermutationCipherKey(override val value: String) : CipherKey<String, Pair<List<Int>, List<Int>>> {
    override fun formatKey(): Result<Pair<List<Int>, List<Int>>> = runCatching {
        val sizes = parseStringToPair(value)
        try {
            Pair(generateRandomList(sizes.first), generateRandomList(sizes.second))
        } catch (e: Exception) {
            if (e is IllegalArgumentException) {
                e.message?.let {
                    error(it)
                }
            }
            runBlocking {
                error(e.message ?: getString(Res.string.numbers_warning))
            }
        }
    }

    fun generateRandomList(n: Int): List<Int> {
        require(n > 1) { "Length of key must be more than 1" }
        val list = (1..n).toMutableList()
        list.shuffle(Random)
        return list
    }

    // 1. add exception for this parsing
    // 2. input format is "n m"
    fun parseStringToPair(input: String): Pair<Int, Int> {
        val (first, second) = input.split(" ").map { it.toInt() }
        return Pair(first, second)
    }

}
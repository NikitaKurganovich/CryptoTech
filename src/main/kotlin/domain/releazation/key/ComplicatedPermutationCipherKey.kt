package domain.releazation.key

import dev.bababnanick.crypto_decoding.generated.resources.*
import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.one_length_warning
import domain.base.CipherKey
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

class ComplicatedPermutationCipherKey(override val value: String) : CipherKey<String, Pair<List<Int>, List<Int>>> {
    override fun formatKey(): Result<Pair<List<Int>, List<Int>>> = runCatching {
        val sizes = parseStringToPair(value)
        Pair(generateRandomList(sizes.first), generateRandomList(sizes.second))
    }

    private fun generateRandomList(n: Int): List<Int> {
        require(n > 1) { runBlocking { getString(Res.string.one_length_warning) } }
        return (1..n).shuffled()
    }

    private fun parseStringToPair(input: String): Pair<Int, Int> {
        try {
            val (first, second) =
                input.split(" ").map { it.toInt() }
            return Pair(first, second)
        } catch (e: Exception) {
            runBlocking {
                error(getString(Res.string.format_warning))
            }
        }
    }
}
package dev.crypto.labfirst.releazation.key

import dev.crypto.base.interfaces.CipherKey
import dev.crypto.labfirst.FirstLabErrors

class ComplicatedPermutationCipherKey(override val value: String) :
    CipherKey<String, Pair<List<Int>, List<Int>>> {
    override fun formatKey(): Result<Pair<List<Int>, List<Int>>> = runCatching {
        val sizes = parseStringToPair(value)
        Pair(generateRandomList(sizes.first), generateRandomList(sizes.second))
    }

    private fun generateRandomList(n: Int): List<Int> {
        require(n > 1) { FirstLabErrors.ShortKey }
        return (1..n).shuffled()
    }

    private fun parseStringToPair(input: String): Pair<Int, Int> {
        try {
            val (first, second) =
                input.split(" ").map { it.toInt() }
            return Pair(first, second)
        } catch (e: Exception) {
            error(FirstLabErrors.KeyFormat)
        }
    }
}
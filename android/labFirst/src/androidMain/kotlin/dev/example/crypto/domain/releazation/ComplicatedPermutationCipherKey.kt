package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.InputErrors
import dev.example.crypto.domain.base.CipherKey
import kotlin.random.Random

class ComplicatedPermutationCipherKey(override val value: String) :
    CipherKey<String, Pair<List<Int>, List<Int>>> {
    override fun formatKey(): Result<Pair<List<Int>, List<Int>>> = runCatching {
        val sizes = parseStringToPair(value)
        Pair(generateRandomList(sizes.first), generateRandomList(sizes.second))
    }

    private fun generateRandomList(n: Int): List<Int> {
        require(n > 1) { InputErrors.ShortKey }
        val list = (1..n).toMutableList()
        list.shuffle(Random)
        return list
    }

    // 1. add exception for this parsing
    // 2. input format is "n m"
    private fun parseStringToPair(input: String): Pair<Int, Int> {
        val (first, second) = input.split(
            delimiters = arrayOf(" "),
            limit = 2
        ).map {
            StringIntCipherKey(it).formatKey().getOrThrow()
        }
        return Pair(first, second)
    }

}
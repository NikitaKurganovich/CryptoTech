package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.Strings.length_warning
import dev.example.crypto.domain.Strings.numbers_warning
import dev.example.crypto.domain.base.CipherKey
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class ComplicatedPermutationCipherKey(override val value: String) :
    CipherKey<String, Pair<List<Int>, List<Int>>> {
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
                error(e.message ?: numbers_warning)
            }
        }
    }

    private fun generateRandomList(n: Int): List<Int> {
        require(n > 1) { length_warning }
        val list = (1..n).toMutableList()
        list.shuffle(Random)
        return list
    }

    // 1. add exception for this parsing
    // 2. input format is "n m"
    private fun parseStringToPair(input: String): Pair<Int, Int> {
        val (first, second) = input.split(" ").map { it.toInt() }
        return Pair(first, second)
    }

}
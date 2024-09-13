package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.Strings.numbers_warning
import dev.example.crypto.domain.base.CipherKey
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class SimplePermutationCipherKey(override val value: String) : CipherKey<String, List<Int>> {
    override fun formatKey(): Result<List<Int>> = runCatching {
        val size = StringIntCipherKey(value).formatKey().getOrThrow()
        try {
            generateRandomList(size)
        } catch (e: Exception) {
            if (e is IllegalArgumentException) {
                e.message?.let {
                    error(it)
                }
            }

            // delete this exception cause we have this exception on line 14 ??
            runBlocking {
                error(e.message ?: numbers_warning)
            }
        }
    }

    fun generateRandomList(n: Int): List<Int> {
        require(n > 1) { "Length of key must be more than 1" }
        val list = (1..n).toMutableList()
        list.shuffle(Random)
        return list
    }
}
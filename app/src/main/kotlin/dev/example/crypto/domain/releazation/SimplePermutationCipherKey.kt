package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.Strings.length_warning
import dev.example.crypto.domain.base.CipherKey
import kotlin.random.Random

class SimplePermutationCipherKey(override val value: String) : CipherKey<String, List<Int>> {
    override fun formatKey(): Result<List<Int>> = runCatching {
        val size = StringIntCipherKey(value).formatKey().getOrThrow()
        generateRandomList(size)
    }

    private fun generateRandomList(n: Int): List<Int> {
        require(n > 1) { length_warning }
        val list = (1..n).toMutableList()
        list.shuffle(Random)
        return list
    }
}
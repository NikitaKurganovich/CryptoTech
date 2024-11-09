package dev.crypto.labfirst.releazation.key

import dev.crypto.base.interfaces.CipherKey
import dev.crypto.labfirst.FirstLabErrors

class SimplePermutationCipherKey(override val value: String) : CipherKey<String, List<Int>> {
    override fun formatKey(): Result<List<Int>> = runCatching {
        val size = StringIntCipherKey(value).formatKey().getOrThrow()
        try {
            generateRandomList(size)
        } catch (e: Exception) {
            error(e.message ?: FirstLabErrors.Numbers)
        }
    }

    private fun generateRandomList(n: Int): List<Int> {
        require(n > 1) { FirstLabErrors.KeyLength }
        return (1..n).shuffled()
    }
}
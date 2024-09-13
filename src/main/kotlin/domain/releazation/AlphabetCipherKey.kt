package domain.releazation

import domain.base.CipherKey
import java.util.*
import kotlin.random.Random

class AlphabetCipherKey(override val value: String) : CipherKey<String, String> {
    override fun formatKey(): Result<String> = runCatching {
        shuffleAlphabet(value)
    }

    private fun shuffleAlphabet(input: String): String {
        val alphabetList = ('a'..'z').toList()
        val shuffledAlphabetList = alphabetList.shuffled(Random(input.hashCode()))
        val shuffledAlphabet = shuffledAlphabetList.joinToString("")
        return shuffledAlphabet
    }
}
package domain.releazation.key

import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.numbers_warning
import dev.bababnanick.crypto_decoding.generated.resources.one_length_warning
import domain.base.CipherKey
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

class SimplePermutationCipherKey(override val value: String) : CipherKey<String, List<Int>> {
    override fun formatKey(): Result<List<Int>> = runCatching {
        val size = StringIntCipherKey(value).formatKey().getOrThrow()
        try {
            generateRandomList(size)
        } catch (e: Exception) {
            runBlocking {
                error(e.message ?: getString(Res.string.numbers_warning))
            }
        }
    }

    private fun generateRandomList(n: Int): List<Int> {
        require(n > 1) {  runBlocking { getString(Res.string.one_length_warning) }  }
        return (1..n).shuffled()
    }
}
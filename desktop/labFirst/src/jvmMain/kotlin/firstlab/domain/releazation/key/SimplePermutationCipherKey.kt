package firstlab.domain.releazation.key

import firstlab.labfirst.generated.resources.Res
import firstlab.labfirst.generated.resources.numbers_warning
import firstlab.labfirst.generated.resources.one_length_warning
import firstlab.domain.base.CipherKey
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
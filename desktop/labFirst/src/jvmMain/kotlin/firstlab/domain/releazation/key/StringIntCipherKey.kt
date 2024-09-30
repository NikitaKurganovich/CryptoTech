package firstlab.domain.releazation.key

import firstlab.labfirst.generated.resources.Res
import firstlab.labfirst.generated.resources.numbers_warning
import firstlab.domain.base.CipherKey
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

class StringIntCipherKey(override val value: String) : CipherKey<String, Int> {

    override fun formatKey(): Result<Int> = runCatching {
        try {
            value.toInt()
        } catch (e: Exception) {
            runBlocking {
                error(getString(Res.string.numbers_warning))
            }
        }
    }
}
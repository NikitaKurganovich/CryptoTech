package domain.releazation

import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.numbers_warning
import domain.base.CipherKey
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
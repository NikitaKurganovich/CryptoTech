package domain.releazation.key

import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.format_error
import domain.Regexes
import domain.base.CipherKey
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

class VigenereCipherKey(override val value: String) : CipherKey<String, String> {
    override fun formatKey(): Result<String> = runCatching {
        if (!value.contains(Regexes.specialCharacters) && value.isNotEmpty()) {
            value
        } else {
            runBlocking {
                error(getString(Res.string.format_error))
            }
        }
    }
}
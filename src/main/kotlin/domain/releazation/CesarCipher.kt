package domain.releazation

import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.format_error
import domain.Regexes.specialCharacters
import domain.base.Cipher
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

class CesarCipher(
    override val message: String,
    override val key: Int,
) : Cipher<Int> {
    override fun encrypt(): Result<String> = runCatching {
        if (!message.contains(specialCharacters)) {
            message.map {
                Char(
                    (it.code + key - 'a'.code).mod(26) + 'a'.code
                )
            }.joinToString("")
        } else {
            runBlocking {
                error(getString(Res.string.format_error))
            }
        }
    }
}
package domain.releazation

import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.empty_warning
import dev.bababnanick.crypto_decoding.generated.resources.format_error
import domain.Regexes
import domain.Regexes.specialCharacters
import domain.base.Cipher
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

class CesarCipher(
    override val message: String,
    override val key: Int,
) : Cipher<Int> {
    override fun encrypt(): Result<String> = runCatching {
        when {
            message.contains(specialCharacters) -> runBlocking {
                error(getString(Res.string.format_error))
            }

            message.isEmpty() -> runBlocking {
                error(getString(Res.string.empty_warning))
            }

            else -> {
                message.map {
                    Char(
                        (it.code + key - 'a'.code).mod(26) + 'a'.code
                    )
                }.joinToString("")
            }
        }
    }
}
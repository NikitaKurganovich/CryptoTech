package domain.releazation

import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.empty_warning
import dev.bababnanick.crypto_decoding.generated.resources.format_error
import domain.Regexes
import domain.base.Cipher
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

class VigenereCipher(
    override val message: String,
    override val key: String,
) : Cipher<String> {
    override fun encrypt(): Result<String> = runCatching {
        when {
            message.contains(Regexes.specialCharacters) -> runBlocking {
                error(getString(Res.string.format_error))
            }

            message.isEmpty() -> runBlocking {
                error(getString(Res.string.empty_warning))
            }

            else -> {
                message.mapIndexed { index, char ->
                    val keyChar = key[index % key.length]
                    if (char.isLetter()) {
                        val base = if (char.isUpperCase()) 'A' else 'a'
                        val keyBase = if (keyChar.isUpperCase()) 'A' else 'a'
                        ((char - base + (keyChar - keyBase)) % 26 + base.code).toChar()
                    } else {
                        char
                    }
                }.joinToString("")
            }
        }
    }
}
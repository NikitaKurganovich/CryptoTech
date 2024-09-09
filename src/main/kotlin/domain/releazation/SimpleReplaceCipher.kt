package domain.releazation

import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.format_error
import domain.Regexes
import domain.base.Cipher
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

class SimpleReplaceCipher(
    override val message: String,
    override val key: String,
) : Cipher<String> {
    private val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    override fun encrypt(): Result<String> = runCatching {
        if (!message.contains(Regexes.specialCharacters)) {
            message.map { char ->
                val isLowerCase = char.isLowerCase()
                val index = alphabet.indexOf(char.uppercaseChar())
                if (index != -1) {
                    val encryptedChar = key[index]
                    if (isLowerCase) encryptedChar.lowercaseChar() else encryptedChar
                } else {
                    char
                }
            }.joinToString("")
        } else {
            runBlocking {
                error(getString(Res.string.format_error))
            }
        }
    }
}
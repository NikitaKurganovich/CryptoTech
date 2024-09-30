package firstlab.domain.releazation.key

import firstlab.labfirst.generated.resources.Res
import firstlab.labfirst.generated.resources.format_error
import firstlab.domain.Regexes
import firstlab.domain.base.CipherKey
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
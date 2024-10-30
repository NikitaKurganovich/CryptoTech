package dev.crypto.first.domain.releazation.key

import crypto_decoding.desktop.feature.firstlab.generated.resources.Res
import crypto_decoding.desktop.feature.firstlab.generated.resources.format_error
import dev.crypto.base.interfaces.CipherKey
import dev.crypto.first.domain.Regexes
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
package dev.crypto.labfirst.releazation.key

import dev.crypto.base.interfaces.CipherKey
import dev.crypto.labfirst.FirstLabErrors
import dev.crypto.labfirst.Regexes

class VigenereCipherKey(override val value: String) : CipherKey<String, String> {
    override fun formatKey(): Result<String> = runCatching {
        require(!value.contains(Regexes.specialCharacters) && value.isNotEmpty()){
            FirstLabErrors.KeyFormat
        }
        value
    }
}
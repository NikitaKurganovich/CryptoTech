package dev.crypto.labfirst.releazation.key

import dev.crypto.base.interfaces.CipherKey
import dev.crypto.labfirst.FirstLabErrors

class StringIntCipherKey(override val value: String) : CipherKey<String, Int> {

    override fun formatKey(): Result<Int> = runCatching {
        try {
            value.toInt()
        } catch (e: Exception) {
            error(FirstLabErrors.Numbers)
        }
    }
}
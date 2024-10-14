package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.InputErrors
import dev.example.crypto.domain.base.CipherKey

class StringIntCipherKey(override val value: String) : CipherKey<String, Int> {

    override fun formatKey(): Result<Int> = runCatching {
        require(value.isNotEmpty()) { InputErrors.KeyEmpty }
        try {
            value.trim().toInt()
        } catch (e: Exception) {
            error(InputErrors.Numbers)
        }
    }
}
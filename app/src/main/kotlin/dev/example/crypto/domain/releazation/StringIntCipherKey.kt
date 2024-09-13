package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.base.CipherKey

class StringIntCipherKey(override val value: String) : CipherKey<String, Int> {

    override fun formatKey(): Result<Int> = runCatching {
        try {
            value.toInt()
        } catch (e: Exception) {
            error("Error: Only numbers in key field are allowed for this cipher method")
        }
    }
}
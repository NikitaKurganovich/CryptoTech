package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.base.CipherKey
import dev.example.crypto.domain.checkStringKey

class VigenereCipherKey(override val value: String) : CipherKey<String, String> {
    override fun formatKey() = runCatching {
        checkStringKey(
            key = value,
            block = { value }
        )
    }
}
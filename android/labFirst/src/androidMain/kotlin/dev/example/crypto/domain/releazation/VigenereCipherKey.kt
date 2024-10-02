package dev.example.crypto.domain.releazation

import dev.example.crypto.domain.base.CipherKey
import dev.example.crypto.domain.checkMessage

class VigenereCipherKey(override val value: String) : CipherKey<String, String> {
    override fun formatKey(): Result<String> = runCatching {
        checkMessage(
            message = value,
            block = { value }
        )
    }
}
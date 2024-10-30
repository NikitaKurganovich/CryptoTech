package dev.crypto.labsecond

import dev.crypto.base.interfaces.CipherKey


class StringIntCipherKey(override val value: String) : CipherKey<String, Int> {

    override fun formatKey(): Result<Int> = runCatching {
        require(value.isNotEmpty()) { SecondLabErrors.KeyEmpty }
        try {
            value.trim().toInt()
        } catch (e: Exception) {
            error(SecondLabErrors.KeyNotInt)
        }
    }
}


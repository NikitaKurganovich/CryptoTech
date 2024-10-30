package dev.crypto.first.domain.releazation.key

import crypto_decoding.desktop.feature.firstlab.generated.resources.Res
import crypto_decoding.desktop.feature.firstlab.generated.resources.numbers_warning
import dev.crypto.base.interfaces.CipherKey
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

class StringIntCipherKey(override val value: String) : CipherKey<String, Int> {

    override fun formatKey(): Result<Int> = runCatching {
        try {
            value.toInt()
        } catch (e: Exception) {
            runBlocking {
                error(getString(Res.string.numbers_warning))
            }
        }
    }
}
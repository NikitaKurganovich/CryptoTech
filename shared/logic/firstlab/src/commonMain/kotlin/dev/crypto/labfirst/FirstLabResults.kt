package dev.crypto.labfirst

import dev.crypto.base.resources.CryptoResId
import dev.crypto.labfirst.resources.first_generated_key
import org.jetbrains.compose.resources.StringResource

enum class FirstLabResults(override val res: StringResource): CryptoResId {
    CryptoWithGeneratedKey(FirstLabRes.first_generated_key)
}
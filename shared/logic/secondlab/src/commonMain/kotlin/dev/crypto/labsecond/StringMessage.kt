package dev.crypto.labsecond

import dev.crypto.base.Regexes
import dev.crypto.base.interfaces.CipherMessage

class StringMessage(
    override val value: String,
    val mode: CryptoMode
) : CipherMessage<String, String> {
    override fun formatMessage(): Result<String> = runCatching {
        require(value.isNotEmpty()) { SecondLabErrors.MessageEmpty }

        when (mode) {
            CryptoMode.Encrypting -> {
                require(!value.contains(Regexes.onlyAlphabet)) { SecondLabErrors.MessageFormat }
                require(!value.contentEquals(" ")) { SecondLabErrors.MessageFormat }
                value
            }

            CryptoMode.Decrypting -> {
                require(!value.contains(Regexes.onlyNumbers)){ SecondLabErrors.MessageFormat }
                value
            }
        }
    }
}
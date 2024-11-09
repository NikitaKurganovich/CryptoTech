package dev.crypto.labfirst

sealed class FirstLabIntent {
    data class MessageFieldChange(val message: String) : FirstLabIntent()
    data class KeyFieldChange(val key: String) : FirstLabIntent()
    data class SwitchChange(val switch: Boolean) : FirstLabIntent()
    data object Act : FirstLabIntent()

    data class ChangeCipher(val cipher: Ciphers) : FirstLabIntent()
}
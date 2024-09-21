package ui.states.implementation

sealed class CryptoButtonState(
    val label: String,
    val onClick: () ->  Unit
){
    data class Decrypt(
        val decryptLabel: String,
        val onDecrypt: () ->  Unit
    ) : CryptoButtonState(decryptLabel, onDecrypt)
    data class Encrypt(
        val encryptLabel: String,
        val onEncrypt: () ->  Unit
    ) : CryptoButtonState(encryptLabel, onEncrypt)
}
package dev.crypto.labfirst

import dev.crypto.base.resources.ResultMessage
import dev.crypto.labfirst.resources.first_about_cesar_key
import dev.crypto.labfirst.resources.first_about_complicated_permutation_key
import dev.crypto.labfirst.resources.first_about_simple_permutation_key
import dev.crypto.labfirst.resources.first_about_simple_replacement_key
import dev.crypto.labfirst.resources.first_about_vigenere_key
import org.jetbrains.compose.resources.StringResource


data class FirstLabState(
    val keyInputFieldText: String,
    val messageInputFieldText: String,
    val infoText: String,
    val resultText: ResultMessage,
    val isErrorResult: Boolean,
    val currentMethod: Ciphers,
    val isDecryption: Boolean
)

enum class Ciphers(
    val cipher: String,
    val keyDescription: KeyDescription
) {
    CESAR(
        cipher = "Cesar",
        keyDescription = KeyDescription(FirstLabRes.first_about_cesar_key)
    ),
    SIMPLE_REPLACEMENT(
        cipher = "Simple Replacement",
        keyDescription = KeyDescription(FirstLabRes.first_about_simple_replacement_key)
    ),
    VIGENERE(
        cipher = "Vigenere",
        keyDescription = KeyDescription(FirstLabRes.first_about_vigenere_key)
    ),
    SIMPLE_PERMUTATION(
        cipher = "Simple Permutation",
        keyDescription = KeyDescription(FirstLabRes.first_about_simple_permutation_key)
    ),
    COMPLICATED_PERMUTATION(
        cipher = "Complicated Permutation",
        keyDescription = KeyDescription(FirstLabRes.first_about_complicated_permutation_key)
    )
}

data class KeyDescription(
    val key: StringResource
){
    operator fun invoke(): StringResource = key
}
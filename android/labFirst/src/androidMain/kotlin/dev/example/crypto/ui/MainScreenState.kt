package dev.example.crypto.ui

import dev.crypto.labfirst.R
import dev.example.crypto.domain.ResultMessage


data class MainScreenState(
    val keyInputFieldText: String,
    val messageInputFieldText: String,
    val infoText: String,
    val resultText: ResultMessage,
    val isErrorResult: Boolean = false,
    val currentMethod: Ciphers = Ciphers.CESAR
)

enum class Ciphers(
    val cipher: String,
    val keyDescription: KeyDescription
) {
    CESAR(
        cipher = "Cesar",
        keyDescription = KeyDescription(R.string.first_about_cesar_key)
    ),
    SIMPLE_REPLACEMENT(
        cipher = "Simple Replacement",
        keyDescription = KeyDescription(R.string.first_about_simple_replacement_key)
    ),
    VIGENERE(
        cipher = "Vigenere",
        keyDescription = KeyDescription(R.string.first_about_vigenere_key)
    ),
    SIMPLE_PERMUTATION(
        cipher = "Simple Permutation",
        keyDescription = KeyDescription(R.string.first_about_simple_permutation_key)
    ),
    COMPLICATED_PERMUTATION(
        cipher = "Complicated Permutation",
        keyDescription = KeyDescription(R.string.first_about_complicated_permutation_key)
    )
}

data class KeyDescription(
    val key: Int
){
    operator fun invoke(): Int = key
}
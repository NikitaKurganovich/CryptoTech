package dev.crypto.labthird

enum class GenerationOptions(val charset: List<Char>) {
    Letters(('a'..'z') + ('A'..'Z')),
    Digits(('0'..'9').toList()),
    SpecialCharacters(listOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')')),
}
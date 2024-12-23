package dev.crypto.labthird

import dev.crypto.base.resources.ResultMessage

data class ThirdLabState(
    val requirements: PasswordStrengthRequirements,
    val isLettersSelected: Boolean,
    val isDigitsSelected: Boolean,
    val isSpecialCharactersSelected: Boolean,
    val isAllSelected: Boolean,
    val passwordLength: Int?,
    val actualCharset: List<Char>,
    val resultMessage: ResultMessage,
    val isError: Boolean
)
package dev.crypto.labthird

import dev.crypto.base.resources.ResultMessage

data class ThirdLabState(
    val isLettersSelected: Boolean,
    val isDigitsSelected: Boolean,
    val isSpecialCharactersSelected: Boolean,
    val passwordLength: Int?,
    val actualCharset: List<Char>,
    val resultMessage: ResultMessage
)
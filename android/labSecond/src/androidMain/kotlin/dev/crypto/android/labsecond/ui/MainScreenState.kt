package dev.crypto.android.labsecond.ui

import dev.crypto.base.resources.ResultMessage
import dev.crypto.labsecond.CryptoMode

data class MainScreenState(
    val encryptionMode: CryptoMode,
    val messageFieldValue: String,
    val qKeyFieldValue: String,
    val pKeyFieldValue: String,
    val eKeyFieldValue: String,
    val result: ResultMessage,
    val isError: Boolean
)
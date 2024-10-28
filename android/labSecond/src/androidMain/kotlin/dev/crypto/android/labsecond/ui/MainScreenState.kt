package dev.crypto.android.labsecond.ui

data class MainScreenState(
    val isEncrypting: Boolean,
    val messageFieldValue: String,
    val qKeyFieldValue: String,
    val pKeyFieldValue: String,
    val eKeyFieldValue: String,
    val result: String,
    val isError: Boolean
)
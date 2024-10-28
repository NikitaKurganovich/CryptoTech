package dev.crypto.android.labsecond.ui

sealed class MainScreenIntent {
    data class MessageFieldChange(val message: String) : MainScreenIntent()
    data class SwitchChange(val switch: Boolean) : MainScreenIntent()

    data class PKeyFieldChange(val key: String) : MainScreenIntent()
    data class QKeyFieldChange(val key: String) : MainScreenIntent()
    data class EKeyFieldChange(val key: String) : MainScreenIntent()

    data object Perform : MainScreenIntent()
}
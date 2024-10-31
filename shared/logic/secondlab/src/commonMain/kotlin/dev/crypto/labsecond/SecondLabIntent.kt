package dev.crypto.labsecond

sealed class SecondLabIntent {
    data class MessageFieldChange(val message: String) : SecondLabIntent()
    data class SwitchChange(val switch: Boolean) : SecondLabIntent()

    data class PKeyFieldChange(val key: String) : SecondLabIntent()
    data class QKeyFieldChange(val key: String) : SecondLabIntent()
    data class EKeyFieldChange(val key: String) : SecondLabIntent()

    data object Perform : SecondLabIntent()
}
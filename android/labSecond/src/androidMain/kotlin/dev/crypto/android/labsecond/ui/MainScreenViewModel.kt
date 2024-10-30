package dev.crypto.android.labsecond.ui

import androidx.lifecycle.ViewModel
import dev.crypto.labsecond.IntKeyTriplet
import dev.crypto.labsecond.RSA
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel : ViewModel() {
    private val _state = MutableStateFlow(
        MainScreenState(
            isEncrypting = true,
            messageFieldValue = "",
            pKeyFieldValue = "",
            qKeyFieldValue = "",
            eKeyFieldValue = "",
            result = "",
            isError = false
        )
    )
    val state: StateFlow<MainScreenState> = _state.asStateFlow()

    fun processIntent(intent: MainScreenIntent) =
        when (intent) {
            is MainScreenIntent.Perform -> perform()
            is MainScreenIntent.SwitchChange -> switchChange(intent.switch)
            is MainScreenIntent.MessageFieldChange -> messageFieldChange(intent.message)
            is MainScreenIntent.PKeyFieldChange -> pKeyFieldChange(intent.key)
            is MainScreenIntent.QKeyFieldChange -> qKeyFieldChange(intent.key)
            is MainScreenIntent.EKeyFieldChange -> eKeyFieldChange(intent.key)
        }

    private fun switchChange(isEncryption: Boolean) =
        _state.update {
            it.copy(isEncrypting = isEncryption)
        }

    private fun messageFieldChange(value: String) =
        _state.update {
            it.copy(messageFieldValue = value)
        }

    private fun pKeyFieldChange(value: String) =
        _state.update {
            it.copy(pKeyFieldValue = value)
        }

    private fun qKeyFieldChange(value: String) =
        _state.update {
            it.copy(qKeyFieldValue = value)
        }

    private fun eKeyFieldChange(value: String) =
        _state.update {
            it.copy(eKeyFieldValue = value)
        }

    private fun perform() = runCatching {
        val rsa = RSA(
            message = _state.value.messageFieldValue,
            key = IntKeyTriplet(
                p = _state.value.pKeyFieldValue.toInt(),
                q = _state.value.qKeyFieldValue.toInt(),
                e = _state.value.eKeyFieldValue.toInt()
            )
        )
        if (_state.value.isEncrypting) {
            rsa.encrypt().processResult()
        } else {
            rsa.decrypt().processResult()
        }
    }

    private fun <T> Result<T>.processResult() =
        this.onSuccess { newValue ->
            _state.update {
                it.copy(
                    isError = false,
                    result = newValue.toString()
                )
            }
        }
            .onFailure { error ->
                _state.update {
                    it.copy(
                        isError = true,
                        result = error.message.toString()
                    )
                }
            }
}
package dev.crypto.android.labsecond.ui

import androidx.lifecycle.ViewModel
import dev.crypto.base.resources.ResultMessage
import dev.crypto.labsecond.CryptoMode
import dev.crypto.labsecond.IntKeyTriplet
import dev.crypto.labsecond.RSA
import dev.crypto.labsecond.SecondLabErrors
import dev.crypto.labsecond.SecondLabIntent
import dev.crypto.labsecond.StringIntCipherKey
import dev.crypto.labsecond.StringMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel : ViewModel() {
    private val _state = MutableStateFlow(
        SecondLabScreenState(
            encryptionMode = CryptoMode.Encrypting,
            messageFieldValue = "",
            pKeyFieldValue = "",
            qKeyFieldValue = "",
            eKeyFieldValue = "",
            result = ResultMessage.StringMessage(""),
            isError = false
        )
    )
    val state: StateFlow<SecondLabScreenState> = _state.asStateFlow()

    fun processIntent(intent: SecondLabIntent) =
        when (intent) {
            is SecondLabIntent.Perform -> perform()
            is SecondLabIntent.SwitchChange -> switchChange(intent.switch)
            is SecondLabIntent.MessageFieldChange -> messageFieldChange(intent.message)
            is SecondLabIntent.PKeyFieldChange -> pKeyFieldChange(intent.key)
            is SecondLabIntent.QKeyFieldChange -> qKeyFieldChange(intent.key)
            is SecondLabIntent.EKeyFieldChange -> eKeyFieldChange(intent.key)
        }

    private fun switchChange(isEncryption: Boolean) {
        _state.update {
            it.copy(
                encryptionMode = if (isEncryption) {
                    CryptoMode.Encrypting
                } else {
                    CryptoMode.Decrypting
                }
            )
        }
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

    private fun perform() {
        runCatching<ResultMessage> {
            val rsa = RSA(
                message = StringMessage(
                    value = _state.value.messageFieldValue,
                    mode = _state.value.encryptionMode
                ).formatMessage().getOrThrow(),
                key = IntKeyTriplet(
                    p = StringIntCipherKey(_state.value.pKeyFieldValue).formatKey().getOrThrow(),
                    q = StringIntCipherKey(_state.value.qKeyFieldValue).formatKey().getOrThrow(),
                    e = StringIntCipherKey(_state.value.eKeyFieldValue).formatKey().getOrThrow()
                )
            )
            when (_state.value.encryptionMode) {
                CryptoMode.Decrypting -> rsa.decrypt().getOrThrow()
                CryptoMode.Encrypting -> rsa.encrypt().getOrThrow()
            }
        }.processResult()
    }

    private fun <T: ResultMessage> Result<T>.processResult() =
        this.onSuccess { newValue ->
            _state.update {
                it.copy(
                    isError = false,
                    result = newValue
                )
            }
        }
            .onFailure { error ->
                _state.update {
                    it.copy(
                        isError = true,
                        result = ResultMessage.IdMessage(
                            id = error.message?.let { err ->
                                SecondLabErrors.valueOf(err)
                            } ?: SecondLabErrors.Unspecified
                        )
                    )
                }
            }
}
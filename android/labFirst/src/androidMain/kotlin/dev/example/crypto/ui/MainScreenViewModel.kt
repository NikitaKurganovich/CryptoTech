package dev.example.crypto.ui

import androidx.lifecycle.ViewModel
import dev.example.crypto.domain.CipherFabric
import dev.example.crypto.domain.InputErrors
import dev.example.crypto.domain.ResultMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel : ViewModel() {
    private val _state: MutableStateFlow<MainScreenState> = MutableStateFlow(
        MainScreenState(
            keyInputFieldText = "",
            messageInputFieldText = "",
            infoText = "",
            resultText = ResultMessage.StringMessage(""),
        )
    )
    val state: StateFlow<MainScreenState> = _state.asStateFlow()

    fun onKeyFieldValueChange(
        newValue: String,
    ) {
        resetResultField()
        _state.update { it.copy(keyInputFieldText = newValue) }
    }

    fun onMessageFieldValueChange(
        newValue: String,
    ) {
        resetResultField()
        _state.update {
            it.copy(messageInputFieldText = newValue)
        }
    }

    fun onCipherChange(cipher: Ciphers) {
        _state.update {
            it.copy(currentMethod = cipher)
        }
        resetResultField()
    }

    fun onEncrypt() {
        CipherFabric(
            cipher = state.value.currentMethod,
            key = state.value.keyInputFieldText,
            message = state.value.messageInputFieldText
        ).createCipher()
            .onSuccess { value ->
                value.encrypt().onSuccess {
                    setSuccessfulResult(
                        ResultMessage.StringMessage(it)
                    )
                }
                    .onFailure { err -> setError(err) }
            }
            .onFailure { err -> setError(err) }
    }

    private fun setError(err: Throwable) {
        setErrorResult(
            ResultMessage.IdMessage(
                err.message?.let {
                    InputErrors.valueOf(it).id
                } ?: InputErrors.Unspecified.id
            )
        )
    }

    private fun setSuccessfulResult(value: ResultMessage) {
        _state.update {
            it.copy(
                resultText = value,
                isErrorResult = false
            )
        }
    }

    private fun setErrorResult(exception: ResultMessage) {
        _state.update {
            it.copy(
                resultText = exception,
                isErrorResult = true
            )
        }
    }

    private fun resetResultField() {
        _state.update {
            it.copy(
                resultText = ResultMessage.StringMessage(""),
                isErrorResult = false
            )
        }
    }
}

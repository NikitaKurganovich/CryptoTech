package dev.crypto.labfirst

import dev.crypto.base.interfaces.IntentHandler
import dev.crypto.base.resources.ResultMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FirstLabIntentHandler : IntentHandler<FirstLabState, FirstLabIntent> {
    private val _state = MutableStateFlow(
        FirstLabState(
            keyInputFieldText = "",
            messageInputFieldText = "",
            infoText = "",
            resultText = ResultMessage.StringMessage(""),
            isErrorResult = false,
            currentMethod = Ciphers.CESAR,
            isDecryption = false
        )
    )

    override val state: StateFlow<FirstLabState> = _state.asStateFlow()

    override fun processIntent(intent: FirstLabIntent) {
        when (intent) {
            is FirstLabIntent.KeyFieldChange -> onKeyFieldValueChange(intent.key)
            is FirstLabIntent.MessageFieldChange -> onMessageFieldValueChange(intent.message)
            is FirstLabIntent.SwitchChange -> onCryptoMethodChange(intent.switch)
            is FirstLabIntent.Act -> onAct()
            is FirstLabIntent.ChangeCipher -> onCipherChange(intent.cipher)
        }
    }

    private fun onAct() {
        if (state.value.isDecryption) {
            onDecrypt()
        } else {
            onEncrypt()
        }
    }

    private fun onCryptoMethodChange(switch: Boolean) {
        resetResultField()
        _state.update { it.copy(isDecryption = switch) }
    }

    private fun onKeyFieldValueChange(
        newValue: String,
    ) {
        resetResultField()
        _state.update { it.copy(keyInputFieldText = newValue) }
    }

    private fun onMessageFieldValueChange(
        newValue: String,
    ) {
        resetResultField()
        _state.update {
            it.copy(messageInputFieldText = newValue)
        }
    }

    private fun setError(err: Throwable) {
        setErrorResult(
            ResultMessage.IdMessage(
                err.message?.let {
                    FirstLabErrors.valueOf(it)
                } ?: FirstLabErrors.Unspecified
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

    private fun onCipherChange(cipher: Ciphers) {
        _state.update {
            it.copy(
                currentMethod = cipher
            )
        }
        resetResultField()
    }

    private fun onEncrypt() {
        CipherFabric(
            cipher = state.value.currentMethod,
            key = state.value.keyInputFieldText,
            message = state.value.messageInputFieldText
        ).createCipher()
            .onSuccess { value ->
                value.encrypt().onSuccess { setSuccessfulResult(it) }
                    .onFailure { err -> setError(err) }
            }
            .onFailure { err -> setError(err) }
    }

    private fun onDecrypt() {
        CipherFabric(
            cipher = state.value.currentMethod,
            key = state.value.keyInputFieldText,
            message = state.value.messageInputFieldText
        ).createCipher()
            .onSuccess { value ->
                value.decrypt().onSuccess { setSuccessfulResult(it) }
                    .onFailure { err -> setError(err) }
            }
            .onFailure { err -> setError(err) }
    }
}
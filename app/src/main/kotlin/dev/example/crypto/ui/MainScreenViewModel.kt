package dev.example.crypto.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.example.crypto.domain.CipherFabric
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import dev.example.crypto.ui.states.implementation.FieldStateImpl
import dev.example.crypto.ui.states.implementation.TextStateImpl

class MainScreenViewModel : ViewModel() {
    private val _state: MutableStateFlow<MainScreenState> = MutableStateFlow(
        MainScreenState(
            keyInputFieldState = FieldStateImpl(
                value = "",
                label = "Key"
            ),
            messageInputFieldState = FieldStateImpl(
                value = "",
                label = "Message"
            ),
            infoTextState = TextStateImpl(
                label = "",
                isError = false
            ),
            resultTextState = TextStateImpl(
                label = "",
                isError = false
            )
        )
    )
    val state: StateFlow<MainScreenState> = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                infoTextState = TextStateImpl(
                    label = "Enter a message and a key",
                    isError = false
                )
            )
        }
    }

    fun onKeyFieldValueChange(
        newValue: String,
    ) {
        resetResultField()
        _state.update {
            it.copy(
                keyInputFieldState = it.keyInputFieldState.onValueChange(newValue)
            )
        }
    }

    fun onMessageFieldValueChange(
        newValue: String,
    ) {
        resetResultField()
        _state.update {
            it.copy(
                messageInputFieldState = it.messageInputFieldState.onValueChange(newValue)
            )
        }
    }

    fun onCipherChange(cipher: Ciphers) {
        _state.update {
            it.copy(
                currentMethod = cipher
            )
        }
        resetResultField()
    }

    fun onEncrypt() {
        CipherFabric(
            cipher = state.value.currentMethod,
            key = state.value.keyInputFieldState.value,
            message = state.value.messageInputFieldState.value
        ).createCipher()
            .onSuccess { value ->
                value.encrypt().onSuccess { setSuccessfulResult(it) }
            }
            .onFailure { err -> setError(err) }
    }

    private fun setError(err: Throwable) {
        viewModelScope.launch {
            setErrorResult(err.message ?: "Something went wrong. Please try again")
        }
    }

    private fun setSuccessfulResult(value: String) {
        _state.update {
            it.copy(
                resultTextState = TextStateImpl(
                    label = value,
                    isError = false
                )
            )
        }
    }

    private fun setErrorResult(exception: String) {
        _state.update {
            it.copy(
                resultTextState = TextStateImpl(
                    label = exception,
                    isError = true
                )
            )
        }
    }

    private fun resetResultField() {
        _state.update {
            it.copy(
                resultTextState = TextStateImpl(
                    label = " ",
                    isError = false
                )
            )
        }
    }
}

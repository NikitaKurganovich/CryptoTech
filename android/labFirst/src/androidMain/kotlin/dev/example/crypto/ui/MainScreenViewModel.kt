package dev.example.crypto.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.example.crypto.domain.CipherFabric
import dev.example.crypto.domain.Strings.results
import dev.example.crypto.domain.Strings.unspecified_error
import dev.example.crypto.domain.findRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidkit.states.implementation.FieldStateImpl
import androidkit.states.implementation.KeyFieldStateImpl
import androidkit.states.implementation.TextStateImpl
import dev.example.crypto.R

class MainScreenViewModel : ViewModel() {
    private val _state: MutableStateFlow<MainScreenState> = MutableStateFlow(
        MainScreenState(
            keyInputFieldState = KeyFieldStateImpl(
                value = "",
                label = "Key",
                aboutKeyRes = R.string.about_cesar_key
            ),
            messageInputFieldState = FieldStateImpl(
                value = "",
                label = "Message"
            ),
            infoTextState = TextStateImpl(
                label = results,
                isError = false
            ),
            resultTextState = TextStateImpl(
                label = "",
                isError = false
            )
        )
    )
    val state: StateFlow<MainScreenState> = _state.asStateFlow()

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
                currentMethod = cipher,
                keyInputFieldState = KeyFieldStateImpl(
                    value = it.keyInputFieldState.value,
                    label = it.keyInputFieldState.label,
                    aboutKeyRes = findRes(cipher)
                )
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
                    .onFailure { err -> setError(err) }
            }
            .onFailure { err -> setError(err) }
    }

    private fun setError(err: Throwable) {
        viewModelScope.launch {
            setErrorResult(err.message ?: unspecified_error)
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
                    label = "",
                    isError = false
                )
            )
        }
    }
}

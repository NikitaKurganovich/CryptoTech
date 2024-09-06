package ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dev.bababnanick.crypto_decoding.generated.resources.*
import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.empty
import dev.bababnanick.crypto_decoding.generated.resources.result
import domain.releazation.CesarCipher
import domain.releazation.StringIntCipherKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import ui.states.implementation.InputFieldStateImpl
import ui.states.implementation.TextStateImpl

class MainScreenModel : ScreenModel {
    private val _state: MutableStateFlow<MainScreenState> = MutableStateFlow(
        MainScreenState(
            keyInputFieldState = InputFieldStateImpl(
                value = "",
                label = "Key"
            ),
            messageInputFieldState = InputFieldStateImpl(
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
        screenModelScope.launch {
            _state.update {
                it.copy(
                    infoTextState = TextStateImpl(
                        label = getString(Res.string.result),
                        isError = false
                    )
                )
            }
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

    fun onEncrypt() {
        StringIntCipherKey(
            value = state.value.keyInputFieldState.value
        ).formatKey()
            .onSuccess { keyValue ->
                CesarCipher(
                    message = state.value.messageInputFieldState.value,
                    key = keyValue
                ).encrypt()
                    .onSuccess { value ->
                        setSuccessfulResult(value)
                    }
                    .onFailure { err ->
                        screenModelScope.launch {
                            setErrorResult(err.message ?: getString(Res.string.unspecified_error))
                        }
                    }
            }.onFailure { err ->
                screenModelScope.launch {
                    setErrorResult(err.message ?: getString(Res.string.unspecified_error))
                }
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
        screenModelScope.launch {
            _state.update {
                it.copy(
                    resultTextState = TextStateImpl(
                        label = getString(Res.string.empty),
                        isError = false
                    )
                )
            }
        }
    }

}
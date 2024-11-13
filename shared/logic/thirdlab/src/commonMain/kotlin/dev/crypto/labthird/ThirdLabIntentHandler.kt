package dev.crypto.labthird

import dev.crypto.base.interfaces.IntentHandler
import dev.crypto.base.resources.ResultMessage
import io.nacular.measured.units.times
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.log

class ThirdLabIntentHandler(
    private val requirements: PasswordStrengthRequirements
) : IntentHandler<ThirdLabState, ThirdLabIntent> {

    private val _state = MutableStateFlow(
        ThirdLabState(
            isDigitsSelected = false,
            isLettersSelected = false,
            isSpecialCharactersSelected = false,
            passwordLength = null,
            isAllSelected = false,
            actualCharset = emptyList(),
            resultMessage = ResultMessage.StringMessage(""),
            isError = false
        )
    )

    override val state: StateFlow<ThirdLabState> = _state.asStateFlow().also {
        _state.update {
            it.copy(isAllSelected = it.isDigitsSelected && it.isLettersSelected && it.isSpecialCharactersSelected)
        }
    }

    override fun processIntent(intent: ThirdLabIntent) {
        runCatching {
            when (intent) {
                is ThirdLabIntent.SetGenerationOption -> changeGenerationOption(intent.option)
                is ThirdLabIntent.GeneratePassword -> generatePassword()
                is ThirdLabIntent.AllOptionsSelected -> addAllOptions()
            }
        }.onFailure { error ->
            _state.update {
                it.copy(
                    isError = true,
                    resultMessage = ResultMessage.IdMessage(
                        ThirdLabErrorResults.valueOf(
                            error.message ?: error(error.message.toString())
                        )
                    )
                )
            }
        }

    }

    private fun addAllOptions() {
        for (option in GenerationOptions.entries) changeGenerationOption(option)
    }

    private fun changeGenerationOption(option: GenerationOptions) {
        _state.update {
            when (option) {
                GenerationOptions.Digits ->
                    it.copy(isDigitsSelected = !it.isDigitsSelected)

                GenerationOptions.SpecialCharacters ->
                    it.copy(isSpecialCharactersSelected = !it.isSpecialCharactersSelected)

                GenerationOptions.Letters -> it.copy(isLettersSelected = !it.isLettersSelected)
            }
        }
        configureCharset()
        updatePasswordLength()
    }

    private fun generatePassword() {
        configureCharset()
        val password = state.value.passwordLength?.let {
            CharsetBasedPassword(
                charset = _state.value.actualCharset,
                requiredLength = it
            ).generatePassword()
        } ?: error(ThirdLabErrorResults.CharsetEmpty)
        _state.update {
            it.copy(
                resultMessage = ResultMessage.IdMessage(
                    id = ThirdLabSuccessResults.PasswordGenerated,
                    args = arrayOf(password)
                )
            )
        }
    }

    private fun configureCharset() {
        val charset = getCharset()
        _state.update {
            it.copy(actualCharset = charset)
        }
    }

    private fun getCharset(): List<Char> =
        with(state.value) {
            if (isDigitsSelected) {
                GenerationOptions.Digits.charset
            } else {
                emptyList<Char>()
            } +
                    if (isSpecialCharactersSelected) {
                        GenerationOptions.SpecialCharacters.charset
                    } else {
                        emptyList<Char>()
                    } +
                    if (isLettersSelected) {
                        GenerationOptions.Letters.charset
                    } else {
                        emptyList()
                    }
        }


    private fun updatePasswordLength() {
        val x =
            (requirements.bruteForceSpeed * requirements.timeToBruteForce) / requirements.probability
        val base = state.value.actualCharset.size
        _state.update {
            it.copy(
                passwordLength = log(
                    x = x.amount,
                    base = base.toDouble()
                ).toInt()
            )
        }
    }
}
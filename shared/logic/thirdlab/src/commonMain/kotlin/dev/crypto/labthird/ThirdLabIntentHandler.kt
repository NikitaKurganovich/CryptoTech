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
            requirements = requirements,
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

    override val state: StateFlow<ThirdLabState> = _state.asStateFlow()

    override fun processIntent(intent: ThirdLabIntent) {
        runCatching {
            when (intent) {
                is ThirdLabIntent.SetGenerationOption -> changeGenerationOption(intent.option)
                is ThirdLabIntent.GeneratePassword -> generatePassword(intent.block)
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
        with(_state.value) {
            _state.update {
                if (isAllSelected) {
                    it.copy(
                        isDigitsSelected = false,
                        isLettersSelected = false,
                        isSpecialCharactersSelected = false,
                        isAllSelected = false
                    )
                } else {
                    it.copy(
                        isDigitsSelected = true,
                        isLettersSelected = true,
                        isSpecialCharactersSelected = true,
                        isAllSelected = true
                    )
                }
            }
        }
        configureCharset()
    }

    private fun changeGenerationOption(option: GenerationOptions) {
        changeStateViaOption(option)
        adjustSelection()
        configureCharset()
        updatePasswordLength()
    }

    private fun changeStateViaOption(option: GenerationOptions) {
        _state.update {
            when (option) {
                GenerationOptions.Digits ->
                    it.copy(isDigitsSelected = !it.isDigitsSelected)

                GenerationOptions.SpecialCharacters ->
                    it.copy(isSpecialCharactersSelected = !it.isSpecialCharactersSelected)

                GenerationOptions.Letters -> it.copy(isLettersSelected = !it.isLettersSelected)
            }
        }
    }

    private fun adjustSelection() {
        _state.update {
            it.copy(
                isAllSelected = it.isDigitsSelected && it.isLettersSelected && it.isSpecialCharactersSelected,
                isError = false,
                resultMessage = ResultMessage.StringMessage("")
            )
        }
    }

    private fun generatePassword(block: (String)-> Unit) {
        configureCharset()
        val password = state.value.passwordLength?.let { length ->
            CharsetBasedPassword(
                charset = _state.value.actualCharset,
                requiredLength = length
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
        block(password)
    }

    private fun configureCharset() {
        val charset = getCharset()
        _state.update {
            it.copy(actualCharset = charset)
        }
        updatePasswordLength()
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
                passwordLength = if (base != 0) {
                    log(
                        x = x.amount,
                        base = base.toDouble()
                    ).toInt()
                } else null
            )
        }
    }
}
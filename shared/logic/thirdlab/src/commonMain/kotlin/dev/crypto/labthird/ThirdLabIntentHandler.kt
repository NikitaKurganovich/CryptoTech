package dev.crypto.labthird

import dev.crypto.base.interfaces.IntentHandler
import dev.crypto.base.resources.ResultMessage
import io.nacular.measured.units.times
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
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
            actualCharset = emptyList(),
            resultMessage = ResultMessage.StringMessage("")
        )
    )

    override val state: StateFlow<ThirdLabState> = _state.asStateFlow()

    override fun processIntent(intent: ThirdLabIntent) {
        runBlocking {
            when (intent) {
                is ThirdLabIntent.SetGenerationOption -> TODO()
                is ThirdLabIntent.GeneratePassword -> generatePassword()
                is ThirdLabIntent.AllOptionsSelected -> TODO()
            }
        }
    }

    private fun generatePassword() {
        val password = state.value.passwordLength?.let {
            CharsetBasedPassword(
                charset = state.value.actualCharset,
                requiredLength = it
            ).generatePassword()
        } ?: error("")
    }

    private fun configureCharset() {
        val charset = getCharset()
        _state.update {
            it.copy(actualCharset = charset)
        }
    }

    private fun getCharset(): List<Char> =
        with(state.value) {
            if (isDigitsSelected) GenerationOptions.Digits.charset else emptyList<Char>() +
            if (isSpecialCharactersSelected) GenerationOptions.SpecialCharacters.charset
                else emptyList<Char>() +
            if (isLettersSelected) GenerationOptions.Letters.charset else emptyList()
        }


    private fun updatePasswordLength() {
        val x = requirements.bruteForceSpeed * requirements.timeToBruteForce
        val base = requirements.probability * state.value.actualCharset.size.toBigDecimal()
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
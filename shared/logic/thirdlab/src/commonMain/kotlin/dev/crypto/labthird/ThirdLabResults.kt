package dev.crypto.labthird

import dev.crypto.base.resources.CryptoResId
import dev.crypto.labthird.resources.third_charset_empty_warning
import dev.crypto.labthird.resources.third_length_empty_warning
import dev.crypto.labthird.resources.third_password_generated
import org.jetbrains.compose.resources.StringResource

sealed interface ThirdLabResults : CryptoResId

enum class ThirdLabSuccessResults(
    override val errorRes: StringResource
) : ThirdLabResults {
    PasswordGenerated(ThirdLabResources.third_password_generated),
}

enum class ThirdLabErrorResults(
    override val errorRes: StringResource
): ThirdLabResults{
    CharsetEmpty(ThirdLabResources.third_charset_empty_warning),
    PasswordLengthTooShort(ThirdLabResources.third_length_empty_warning)
}
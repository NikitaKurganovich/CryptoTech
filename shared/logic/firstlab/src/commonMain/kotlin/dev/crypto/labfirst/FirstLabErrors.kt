package dev.crypto.labfirst

import dev.crypto.base.resources.CryptoError
import dev.crypto.labfirst.resources.first_empty_key_warning
import dev.crypto.labfirst.resources.first_empty_message_warning
import dev.crypto.labfirst.resources.first_key_format_warning
import dev.crypto.labfirst.resources.first_key_length_warning
import dev.crypto.labfirst.resources.first_length_warning
import dev.crypto.labfirst.resources.first_message_format_warning
import dev.crypto.labfirst.resources.first_numbers_warning
import dev.crypto.labfirst.resources.first_unspecified_error
import org.jetbrains.compose.resources.StringResource

enum class FirstLabErrors(override val errorRes: StringResource): CryptoError {
    MessageFormat(FirstLabRes.first_message_format_warning),
    KeyFormat(FirstLabRes.first_key_format_warning),
    MessageEmpty(FirstLabRes.first_empty_message_warning),
    KeyEmpty(FirstLabRes.first_empty_key_warning),
    KeyLength(FirstLabRes.first_key_length_warning),
    Numbers(FirstLabRes.first_numbers_warning),
    ShortKey(FirstLabRes.first_length_warning),
    Unspecified(FirstLabRes.first_unspecified_error)
}


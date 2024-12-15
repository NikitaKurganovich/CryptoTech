package dev.crypto.labsecond

import dev.crypto.base.resources.CryptoResId
import dev.crypto.labsecond.resources.second_e_greater_phi_error
import dev.crypto.labsecond.resources.second_e_not_coprime_error
import dev.crypto.labsecond.resources.second_key_must_be_int_error
import dev.crypto.labsecond.resources.second_key_must_not_be_empty_error
import dev.crypto.labsecond.resources.second_low_p_and_q_product_error
import dev.crypto.labsecond.resources.second_message_format_error
import dev.crypto.labsecond.resources.second_message_must_not_be_empty_error
import dev.crypto.labsecond.resources.second_not_prime_q_and_p_error
import dev.crypto.labsecond.resources.second_unspecified
import org.jetbrains.compose.resources.StringResource

enum class SecondLabErrors(override val res: StringResource): CryptoResId {
    Unspecified(SecondLabString.second_unspecified),

    LowQAndPProduct(SecondLabString.second_low_p_and_q_product_error),
    EGreaterThanPhi(SecondLabString.second_e_greater_phi_error),
    ENotCoprime(SecondLabString.second_e_not_coprime_error),
    QAndPNotPrime(SecondLabString.second_not_prime_q_and_p_error),

    KeyEmpty(SecondLabString.second_key_must_not_be_empty_error),
    KeyNotInt(SecondLabString.second_key_must_be_int_error),

    MessageEmpty(SecondLabString.second_message_must_not_be_empty_error),
    MessageFormat(SecondLabString.second_message_format_error)
}
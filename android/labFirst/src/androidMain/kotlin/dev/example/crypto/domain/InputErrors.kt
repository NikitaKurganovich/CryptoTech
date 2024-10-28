package dev.example.crypto.domain

import dev.crypto.labfirst.R


enum class InputErrors(val id: ErrorId) {
    MessageFormat(ErrorId(R.string.first_message_format_warning)),
    KeyFormat(ErrorId(R.string.first_key_format_warning)),
    MessageEmpty(ErrorId(R.string.first_empty_message_warning)),
    KeyEmpty(ErrorId(R.string.first_empty_key_warning)),
    KeyLength(ErrorId(R.string.first_key_length_warning)),
    Numbers(ErrorId(R.string.first_numbers_warning)),
    ShortKey(ErrorId(R.string.first_length_warning)),
    Unspecified(ErrorId(R.string.first_unspecified_error))
}

data class ErrorId(val id: Int)
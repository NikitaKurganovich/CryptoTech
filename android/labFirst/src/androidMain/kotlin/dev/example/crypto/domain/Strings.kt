package dev.example.crypto.domain

@Suppress("ConstPropertyName")
object Strings {
    const val format_error = "Only a-z characters allowed"

    const val empty_warning = "Error: Message field can't be empty"
    const val key_length_warning = "Error: Length of key is not correct"

    const val numbers_warning =
        "Error: Only numbers in key field are allowed for this cipher method"

    const val length_warning = "Length of key must be more than 1"

    const val results = "Results:"

    const val unspecified_error = "Something went wrong. Please try again"
}
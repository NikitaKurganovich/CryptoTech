package dev.crypto.labthird

sealed class ThirdLabIntent {
    data class GeneratePassword(val block: (String) -> Unit ) : ThirdLabIntent()
    data class SetGenerationOption(val option: GenerationOptions) : ThirdLabIntent()
    data object AllOptionsSelected : ThirdLabIntent()
}
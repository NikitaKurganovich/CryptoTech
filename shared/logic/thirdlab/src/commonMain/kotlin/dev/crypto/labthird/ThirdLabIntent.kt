package dev.crypto.labthird

sealed class ThirdLabIntent {
    data object GeneratePassword : ThirdLabIntent()
    data class SetGenerationOption(val option: GenerationOptions) : ThirdLabIntent()
    data object AllOptionsSelected : ThirdLabIntent()
}
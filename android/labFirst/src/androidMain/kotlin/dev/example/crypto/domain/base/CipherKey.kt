package dev.example.crypto.domain.base

interface CipherKey <T, R>{
    val value: T
    fun formatKey(): Result<R>
}
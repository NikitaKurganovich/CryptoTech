package dev.crypto.base.interfaces

interface CipherKey <T, R>{
    val value: T
    fun formatKey(): Result<R>
}
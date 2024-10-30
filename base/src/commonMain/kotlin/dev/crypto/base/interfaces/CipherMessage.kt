package dev.crypto.base.interfaces

interface CipherMessage <T, R>{
    val value: T
    fun formatMessage(): Result<R>
}
